package controly.service;

import controly.entity.UserEntity;
import controly.dto.TopicDetailResponse;
import controly.entity.TopicEntity;
import controly.entity.TopicHasFollowersEntity;
import controly.mapper.TopicMapper;
import controly.repository.TopicHasFollowersRepository;
import controly.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static controly.config.Constant.IDNOTFOUND;

@SuppressWarnings("ALL")
@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TopicHasFollowersRepository topicHasFollowersRepository;

    @Autowired
    private ValidationService validation;

    @Autowired
    private UserService userService;

    @Autowired
    private TopicMapper topicMapper;

    public TopicService() {
    }

    public List<TopicEntity> getAllTopics() {
        List<TopicEntity> topics = topicRepository.findAll();
        if (topics.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Didn't fould any topics");
        return topics;
    }

    @Transactional
    public ResponseEntity<TopicDetailResponse> getTopicoById(Long id) {
        if (validation.existsTopico(id)){
            return ResponseEntity.status(404).build();
        }
        TopicEntity topico = topicRepository.findByIdTopic(id);
        TopicDetailResponse topicDetailResponse = new TopicDetailResponse();

        topicDetailResponse.setIdTopic(topico.getIdTopic());
        topicDetailResponse.setName(topico.getName());
        topicDetailResponse.setAbout(topico.getAbout());
        topicDetailResponse.setCountFollowers(topicHasFollowersRepository.countFollowersATopicHas(topico.getIdTopic()));

        return ResponseEntity.status(200).body(topicDetailResponse);
    }

    public ResponseEntity<List<TopicHasFollowersEntity>> getTopicosByIdUser(Long idUser) {
        if (validation.existsUsuario(idUser)) return ResponseEntity.status(404).build();

        List<TopicHasFollowersEntity> topicoEntityList = topicHasFollowersRepository.findByFollowerIdUser(idUser);

        return topicoEntityList.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(topicoEntityList);
    }

    @Transactional
    public ResponseEntity<TopicEntity> postTopicos(@RequestBody TopicEntity topicEntity) {
        topicRepository.save(topicEntity);
        return ResponseEntity.status(201).body(topicEntity);
    }

    @Transactional
    public ResponseEntity<?> followTopico(Long idTopico, Long idUsuario) {
        if (validation.existsTopico(idTopico) || validation.existsUsuario(idUsuario))
            return ResponseEntity.status(404).body(IDNOTFOUND);

        TopicHasFollowersEntity topicoHasSeguidores = topicHasFollowersRepository.findByTopicIdTopicAndUserIdUser(idTopico, idUsuario);

        if (topicoHasSeguidores == null ) {
            TopicEntity topico = topicRepository.findByIdTopic(idTopico);
            UserEntity usuario = userService.getUserById(idUsuario);

            topicoHasSeguidores = new TopicHasFollowersEntity();
            topicoHasSeguidores.setTopic(topico);
            topicoHasSeguidores.setFollower(usuario);

            topicHasFollowersRepository.save(topicoHasSeguidores);

            return ResponseEntity.status(201).body(String.format("Usuario de id %d comecou a seguir topico de id %d.", idTopico, idUsuario));

        } else {

            return ResponseEntity.status(404).body(String.format("Esse usuario já segue esse topico"));

        }


    }

    @Transactional
    public ResponseEntity<?> unfollowTopico(Long idTopico, Long idUsuario) {
        if (validation.existsTopico(idTopico) || validation.existsUsuario(idUsuario))
            return ResponseEntity.status(404).body(IDNOTFOUND);

        TopicHasFollowersEntity topicoHasSeguidores = topicHasFollowersRepository.findByTopicIdTopicAndUserIdUser(idTopico, idUsuario);

        if (topicoHasSeguidores == null ) {
            return ResponseEntity.status(404).body(String.format("Esse usuario não segue esse topico"));
        } else {

            topicHasFollowersRepository.deleteById(topicoHasSeguidores.getId());

            return ResponseEntity.status(201).body(String.format("Usuario de id %d parou de seguir topico de id %d.", idTopico, idUsuario));

        }
    }

    public ResponseEntity<Boolean> userFollowTopic(Long idTopico, Long idUsuario) {
        Optional<TopicHasFollowersEntity> topicoHasSeguidores = topicHasFollowersRepository.userFollowsTopico(idTopico, idUsuario);
        if (topicoHasSeguidores.isPresent()) return ResponseEntity.status(200).body(true);
        return ResponseEntity.status(200).body(false);
    }

    public Integer getCountFollowersByTopic(Long idTopic){
        return topicHasFollowersRepository.countFollowersATopicHas(idTopic);
    }

    public TopicDetailResponse getTopicDetailedFromTopicEntity(TopicEntity topicEntity){
        TopicDetailResponse topicDetailResponse = new TopicDetailResponse();
        topicMapper.getDtoFromTopic(topicEntity, topicDetailResponse);
        topicDetailResponse.setCountFollowers(
                getCountFollowersByTopic(topicDetailResponse.getIdTopic())
        );
        return topicDetailResponse;
    }
}