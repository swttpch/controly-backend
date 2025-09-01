package br.com.controly.controllers;

import br.com.controly.dtos.SimplifiedTopicResponse;
import br.com.controly.dtos.TopicDetailResponse;
import br.com.controly.dtos.TopicFollowResponse;
import br.com.controly.domain.entities.TopicEntity;
import br.com.controly.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/topics")
@CrossOrigin(origins = "*")
public class TopicController {

    @Autowired
    private TopicService topicService;

    public TopicController() {
    }

    //@PostMapping
    //public ResponseEntity<>

    @GetMapping("/{idTopic}/{idUser}")
    public ResponseEntity<TopicDetailResponse> getTopicById(@PathVariable long idTopic, @PathVariable Long idUser) {
        TopicDetailResponse topic = topicService.getTopicDetailedFromTopicEntity(topicService.getTopicById(idTopic), idUser);
        return ResponseEntity.status(200).body(topic);
    }

    @Cacheable(cacheNames = "topics")
    @GetMapping
    public ResponseEntity<List<TopicEntity>> getAllTopics() {
        List<TopicEntity> list = topicService.getAllTopics();
        if (list.isEmpty()) {
            return ResponseEntity.status(204).body(list);
        }
        return ResponseEntity.status(200).body(list);
    }

    @Cacheable(cacheNames = "topicsPageable")
    @GetMapping("/pageable")
    public ResponseEntity<Page<TopicEntity>> getAllTopicsPageable(
            @RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(defaultValue = "1") Integer pageSize,
            @RequestParam(defaultValue = "name") String sortBy) {

        Page<TopicEntity> topics = topicService.getAllTopicsPageable(pageNo, pageSize, sortBy);
        //if (topics.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Didn't found any topics");

        //List<TopicDetailResponse> topicDetailResponseList =
        //        topics.stream()
        //                .map(topic-> topicService.getTopicDetailedFromTopicEntity(topic))
        //                .collect(Collectors.toList());
        //return ResponseEntity.status(200).body(topicDetailResponseList);
        return ResponseEntity.status(200).body(topics);
    }

    @PutMapping("/{idTopic}/{idUser}")
    public ResponseEntity<?> followAndUnfollowTopic(@PathVariable Long idTopic, @PathVariable Long idUser) {
        if (topicService.checkIfUserFollowTopic(idTopic, idUser)) {
            topicService.unfollowTopico(idTopic, idUser);
            return ResponseEntity.status(200).body("Topic unfollowed successfully.");
        }
        topicService.followTopic(idTopic, idUser);
        return ResponseEntity.status(200).body("Topic followed successfully.");
    }

    @PutMapping("mobile/{idTopic}/{idUser}")
    public ResponseEntity<TopicFollowResponse> followAndUnfollowTopicMobile(@PathVariable Long idTopic, @PathVariable Long idUser) {
        if (topicService.checkIfUserFollowTopic(idTopic, idUser)) {
            return ResponseEntity.status(200).body(topicService.unfollowTopicoMobile(idTopic, idUser));
        } else {
            return ResponseEntity.status(200).body(topicService.followTopicMobile(idTopic, idUser));
        }
    }

    @GetMapping("check/{idTopic}/{idUser}")
    public ResponseEntity<Boolean> checkIfUserFollowsTopic(@PathVariable Long idTopic, @PathVariable Long idUser) {
        return ResponseEntity.status(200).body(topicService.checkIfUserFollowTopic(idTopic, idUser));
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<List<SimplifiedTopicResponse>> getTopicsByUser(@PathVariable Long idUser) {
        List<SimplifiedTopicResponse> topics = topicService.getTopicsUserFollows(idUser);
        return ResponseEntity.status(200).body(topics);
    }
}
