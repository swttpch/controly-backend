package controly.service;

import controly.entities.UserEntity;
import controly.dto.TopicoDTO;
import controly.entities.TopicEntity;
import controly.entities.TopicHasFollowersEntity;
import controly.repository.TopicHasFollowersRepository;
import controly.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static controly.config.Constant.IDNOTFOUND;

@SuppressWarnings("ALL")
@Service
public class TopicoService {
    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TopicHasFollowersRepository topicHasFollowersRepository;

    @Autowired
    private ValidationService validation;

    @Autowired
    private UserService userService;

    public TopicoService() {
    }

    public ResponseEntity<List<TopicoDTO>> getTopicos() {
        List<TopicEntity> topicos = topicRepository.findAll();

        if (topicos.isEmpty()) return ResponseEntity.status(204).build();

        List<TopicoDTO> topicoDTOList = new ArrayList<>();


        for (TopicEntity topico : topicos) {

            TopicoDTO topicoDT = new TopicoDTO();

            topicoDT.setIdTopico(topico.getIdTopic());
            topicoDT.setNome(topico.getName());
            topicoDT.setDescricao(topico.getAbout());
            topicoDT.setSeguidores(topicHasFollowersRepository.countFollowersATopicHas(topico.getIdTopic()));

            topicoDTOList.add(topicoDT);

        }

        return topicoDTOList.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(topicoDTOList);
    }

    @Transactional
    public ResponseEntity<TopicoDTO> getTopicoById(Long id) {
        if (validation.existsTopico(id)){
            return ResponseEntity.status(404).build();
        }
        TopicEntity topico = topicRepository.findByIdTopic(id);
        TopicoDTO topicoDTO = new TopicoDTO();

        topicoDTO.setIdTopico(topico.getIdTopic());
        topicoDTO.setNome(topico.getName());
        topicoDTO.setDescricao(topico.getAbout());
        topicoDTO.setSeguidores(topicHasFollowersRepository.countFollowersATopicHas(topico.getIdTopic()));

        return ResponseEntity.status(200).body(topicoDTO);
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

    public ResponseEntity<Boolean> userFollowTopico(Long idTopico, Long idUsuario) {
        Optional<TopicHasFollowersEntity> topicoHasSeguidores = topicHasFollowersRepository.userFollowsTopico(idTopico, idUsuario);
        if (topicoHasSeguidores.isPresent()) return ResponseEntity.status(200).body(true);
        return ResponseEntity.status(200).body(false);
    }
}