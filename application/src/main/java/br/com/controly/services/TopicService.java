package br.com.controly.services;

import br.com.controly.viewmodels.SimplifiedTopicViewModel;
import br.com.controly.viewmodels.TopicDetailViewModel;
import br.com.controly.viewmodels.TopicFollowViewModel;
import br.com.controly.exception.TopicIdNotFould;
import br.com.controly.mappers.TopicMapper;
import br.com.controly.jpa.TopicHasFollowersRepository;
import br.com.controly.jpa.TopicRepository;
import br.com.controly.domain.entities.Topics;
import br.com.controly.domain.entities.TopicFollowers;
import br.com.controly.domain.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.transaction.Transactional;
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

    public Page<Topics> getAllTopicsPageable(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Topics> topics = topicRepository.findAll(pageable);
        if (!topics.hasContent()) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Didn't fould any topics");
        return topics;
    }

    @Transactional
    public Topics getTopicById(Long id) {
        Topics topic = topicRepository.findById(id).orElseThrow(TopicIdNotFould::new);
        return topic;
    }

    @Transactional
    public boolean checkIfUserFollowTopic(Long idTopic, Long idUser) {
        Topics topic = topicRepository.findById(idTopic).orElseThrow(TopicIdNotFould::new);
        Users user = userService.getUserById(idUser);
        Optional<TopicFollowers> topicHasFollowers = topicHasFollowersRepository.userFollowsTopic(topic, user);
        return topicHasFollowers.isPresent();
    }

    public void followTopic(Long idTopic, Long idUser) {
        Topics topic = topicRepository.findById(idTopic).orElseThrow(TopicIdNotFould::new);
        Users user = userService.getUserById(idUser);
        TopicFollowers topicHasFollowers = new TopicFollowers();

    }

    public TopicFollowViewModel followTopicMobile(Long idTopic, Long idUser) {
        Topics topic = topicRepository.findById(idTopic).orElseThrow(TopicIdNotFould::new);
        Users user = userService.getUserById(idUser);

        TopicFollowers topicHasFollowers = new TopicFollowers();
        topicHasFollowers.setFollower(user);
        topicHasFollowers.setTopic(topic);

        topicHasFollowersRepository.save(topicHasFollowers);

        TopicFollowViewModel topicFollowResponse = new TopicFollowViewModel();
        topicFollowResponse.setFollowedsTotal(getCountFollowersByTopic(idTopic));
        topicFollowResponse.setUserHasFollowedTopic(checkIfUserFollowTopic(idTopic, idUser));

        return topicFollowResponse;
    }

    public void unfollowTopico(Long idTopic, Long idUser) {
        TopicFollowers topicHasFollowers = topicHasFollowersRepository
                .findByTopicIdTopicAndUserIdUser(idTopic, idUser);
        topicHasFollowersRepository.delete(topicHasFollowers);
    }

    public TopicFollowViewModel unfollowTopicoMobile(Long idTopic, Long idUser) {
        TopicFollowers topicHasFollowers = topicHasFollowersRepository
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

    public TopicDetailViewModel getTopicDetailedFromTopicEntity(Topics topicEntity, Long idUser) {
        TopicDetailViewModel topicDetailResponse = new TopicDetailViewModel();
        topicMapper.getDtoFromTopic(topicEntity, topicDetailResponse);

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

    public SimplifiedTopicViewModel getSimplifiedTopic(Topics topic) {
        if (topic == null) return null;
        SimplifiedTopicViewModel simplifiedTopic = new SimplifiedTopicViewModel();
        simplifiedTopic.setIdTopic(topic.getId());
        simplifiedTopic.setName(topic.getName());

        return simplifiedTopic;
    }

    public List<Topics> getAllTopicsByUser(Long idUser) {
        return topicHasFollowersRepository.findByFollowerIdUser(idUser)
                .stream().map(topic -> topic.getTopic()).collect(Collectors.toList());
    }

    public List<SimplifiedTopicViewModel> getTopicsUserFollows(Long idUser) {
        return topicHasFollowersRepository.findByFollowerIdUser(idUser)
                .stream()
                .map(topic -> getSimplifiedTopic(topic.getTopic()))
                .collect(Collectors.toList());
    }

    public List<Topics> getAllTopics() {

        List<Topics> list = topicRepository.findAll();
        return list;

    }
}