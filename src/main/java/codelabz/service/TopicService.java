package codelabz.service;

import codelabz.dto.SimplifiedTopicResponse;
import codelabz.dto.TopicDetailResponse;
import codelabz.entity.TopicEntity;
import codelabz.entity.TopicHasFollowersEntity;
import codelabz.entity.UserEntity;
import codelabz.exception.TopicIdNotFould;
import codelabz.mapper.TopicMapper;
import codelabz.repository.TopicHasFollowersRepository;
import codelabz.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TopicHasFollowersRepository topicHasFollowersRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TopicMapper topicMapper;

    public TopicService() {
    }

    public Page<TopicEntity> getAllTopicsPageable(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<TopicEntity> topics = topicRepository.findAll(pageable);
        if (!topics.hasContent()) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Didn't fould any topics");
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
        Optional<TopicHasFollowersEntity> topicHasFollowers = topicHasFollowersRepository.userFollowsTopic(topic,user);
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
        topicDetailResponse.setSvg(topicEntity.getSvg());
        topicDetailResponse.setCreatedAt(topicEntity.getCreatedAt());
        topicDetailResponse.setCountFollowers(
                getCountFollowersByTopic(topicDetailResponse.getIdTopic())
        );
        return topicDetailResponse;
    }

    public SimplifiedTopicResponse getSimplifiedTopic(TopicEntity topic){
        if (topic == null) return null;
        SimplifiedTopicResponse simplifiedTopic = new SimplifiedTopicResponse();
        simplifiedTopic.setIdTopic(topic.getIdTopic());
        simplifiedTopic.setName(topic.getName());
        simplifiedTopic.setSvg(topic.getSvg());
        return simplifiedTopic;
    }

    public List<TopicEntity> getAllTopicsByUser(Long idUser){
        return  topicHasFollowersRepository.findByFollowerIdUser(idUser)
                        .stream().map(topic-> topic.getTopic()).collect(Collectors.toList());
    }

    public List<SimplifiedTopicResponse> getTopicsUserFollows(Long idUser) {
        return  topicHasFollowersRepository.findByFollowerIdUser(idUser)
                .stream()
                .map(topic-> getSimplifiedTopic(topic.getTopic()))
                .collect(Collectors.toList());
    }

    public List<TopicEntity> getAllTopics() {

        List<TopicEntity> list = topicRepository.findAll();
        return list;

    }
}