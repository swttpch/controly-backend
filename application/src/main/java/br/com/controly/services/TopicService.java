package br.com.controly.services;

import br.com.controly.viewmodels.SimplifiedTopicViewModel;
import br.com.controly.viewmodels.TopicDetailViewModel;
import br.com.controly.viewmodels.TopicFollowViewModel;
import br.com.controly.exception.TopicIdNotFould;
import br.com.controly.mappers.TopicMapper;
import br.com.controly.jpa.TopicHasFollowersRepository;
import br.com.controly.jpa.TopicRepository;
import br.com.controly.domain.entities.TopicEntity;
import br.com.controly.domain.entities.TopicHasFollowersEntity;
import br.com.controly.domain.entities.UserEntity;
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
        Optional<TopicHasFollowersEntity> topicHasFollowers = topicHasFollowersRepository.userFollowsTopic(topic, user);
        return topicHasFollowers.isPresent();
    }

    public void followTopic(Long idTopic, Long idUser) {
        TopicEntity topic = topicRepository.findById(idTopic).orElseThrow(TopicIdNotFould::new);
        UserEntity user = userService.getUserById(idUser);
        TopicHasFollowersEntity topicHasFollowers = new TopicHasFollowersEntity();
        topicHasFollowersRepository.save(
                topicHasFollowers.setFollower(user)
                        .setTopic(topic)
        );
    }

    public TopicFollowViewModel followTopicMobile(Long idTopic, Long idUser) {
        TopicEntity topic = topicRepository.findById(idTopic).orElseThrow(TopicIdNotFould::new);
        UserEntity user = userService.getUserById(idUser);

        TopicHasFollowersEntity topicHasFollowers = new TopicHasFollowersEntity();
        topicHasFollowers.setFollower(user);
        topicHasFollowers.setTopic(topic);

        topicHasFollowersRepository.save(topicHasFollowers);

        TopicFollowViewModel topicFollowResponse = new TopicFollowViewModel();
        topicFollowResponse.setFollowedsTotal(getCountFollowersByTopic(idTopic));
        topicFollowResponse.setUserHasFollowedTopic(checkIfUserFollowTopic(idTopic, idUser));

        return topicFollowResponse;
    }

    public void unfollowTopico(Long idTopic, Long idUser) {
        TopicHasFollowersEntity topicHasFollowers = topicHasFollowersRepository
                .findByTopicIdTopicAndUserIdUser(idTopic, idUser);
        topicHasFollowersRepository.delete(topicHasFollowers);
    }

    public TopicFollowViewModel unfollowTopicoMobile(Long idTopic, Long idUser) {
        TopicHasFollowersEntity topicHasFollowers = topicHasFollowersRepository
                .findByTopicIdTopicAndUserIdUser(idTopic, idUser);
        topicHasFollowersRepository.delete(topicHasFollowers);
        TopicFollowViewModel topicFollowResponse = new TopicFollowViewModel();
        topicFollowResponse.setFollowedsTotal(getCountFollowersByTopic(idTopic));
        topicFollowResponse.setUserHasFollowedTopic(checkIfUserFollowTopic(idTopic, idUser));
        return topicFollowResponse;
    }

    public Integer getCountFollowersByTopic(Long idTopic) {
        return topicHasFollowersRepository.countFollowersATopicHas(idTopic);
    }

    public TopicDetailViewModel getTopicDetailedFromTopicEntity(TopicEntity topicEntity, Long idUser) {
        TopicDetailViewModel topicDetailResponse = new TopicDetailViewModel();
        topicMapper.getDtoFromTopic(topicEntity, topicDetailResponse);
        topicDetailResponse.setSvg(topicEntity.getSvg());
        topicDetailResponse.setPng(topicEntity.getPng());
        topicDetailResponse.setCreatedAt(topicEntity.getCreatedAt());
        topicDetailResponse.setCountFollowers(
                getCountFollowersByTopic(topicDetailResponse.getIdTopic())
        );
        if (idUser != null)
            topicDetailResponse.setUserHasFollowed(
                    checkIfUserFollowTopic(topicDetailResponse.getIdTopic(), idUser)
            );
        return topicDetailResponse;
    }

    public SimplifiedTopicViewModel getSimplifiedTopic(TopicEntity topic) {
        if (topic == null) return null;
        SimplifiedTopicViewModel simplifiedTopic = new SimplifiedTopicViewModel();
        simplifiedTopic.setIdTopic(topic.getIdTopic());
        simplifiedTopic.setName(topic.getName());
        simplifiedTopic.setSvg(topic.getSvg());
        simplifiedTopic.setPng(topic.getPng());
        return simplifiedTopic;
    }

    public List<TopicEntity> getAllTopicsByUser(Long idUser) {
        return topicHasFollowersRepository.findByFollowerIdUser(idUser)
                .stream().map(topic -> topic.getTopic()).collect(Collectors.toList());
    }

    public List<SimplifiedTopicViewModel> getTopicsUserFollows(Long idUser) {
        return topicHasFollowersRepository.findByFollowerIdUser(idUser)
                .stream()
                .map(topic -> getSimplifiedTopic(topic.getTopic()))
                .collect(Collectors.toList());
    }

    public List<TopicEntity> getAllTopics() {

        List<TopicEntity> list = topicRepository.findAll();
        return list;

    }
}