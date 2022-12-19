package controly.service;

import controly.dto.TopicDetailResponse;
import controly.entity.TopicEntity;
import controly.entity.TopicHasFollowersEntity;
import controly.entity.UserEntity;
import controly.exception.TopicIdNotFould;
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
        if (topics.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Didn't fould any topics");
        return topics;
    }

    @Transactional
    public TopicEntity getTopicById(Long id) {
        TopicEntity topic = topicRepository.findById(id).orElseThrow(TopicIdNotFould::new);
        return topic;
    }

    @Transactional
    public boolean checkIfUserFollowTopic(Long idTopic, Long idUser) {
        TopicEntity topic = topicRepository.findById(idTopic).orElseThrow(TopicIdNotFould::new);
        UserEntity user = userService.getUserById(idUser);
        Optional<TopicHasFollowersEntity> topicHasFollowers = topicHasFollowersRepository.userFollowsTopico(topic,user);
        return topicHasFollowers.isPresent();
    }

    public void followTopic(Long idTopic, Long idUser){
        TopicEntity topic = topicRepository.findById(idTopic).orElseThrow(TopicIdNotFould::new);
        UserEntity user = userService.getUserById(idUser);
        TopicHasFollowersEntity topicHasFollowers = new TopicHasFollowersEntity();
        topicHasFollowersRepository.save(
                topicHasFollowers.setFollower(user)
                    .setTopic(topic)
        );
    }

    public void unfollowTopico(Long idTopic, Long idUser){
        TopicHasFollowersEntity topicHasFollowers = topicHasFollowersRepository
                .findByTopicIdTopicAndUserIdUser(idTopic, idUser);
        topicHasFollowersRepository.delete(topicHasFollowers);
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