package controly.controller;

import controly.dto.TopicDetailResponse;
import controly.entity.TopicEntity;
import controly.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/topics")
@CrossOrigin(origins = "*")
public class TopicController {

    @Autowired
    private TopicService topicService;

    public TopicController() {
    }

    @GetMapping("/{idTopic}")
    public ResponseEntity<TopicDetailResponse> getTopicById(@PathVariable long idTopic){
        TopicDetailResponse topic = topicService.getTopicDetailedFromTopicEntity(topicService.getTopicById(idTopic));
        return ResponseEntity.status(200).body(topic);
    }

    @GetMapping
    public ResponseEntity<List<TopicDetailResponse>> getAllTopics(){
        List<TopicEntity> topics = topicService.getAllTopics();
        if (topics.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Didn't found any topics");
        List<TopicDetailResponse> topicDetailResponseList =
                topics.stream()
                        .map(topic-> topicService.getTopicDetailedFromTopicEntity(topic))
                        .collect(Collectors.toList());
        return ResponseEntity.status(200).body(topicDetailResponseList);
    }

    @PutMapping("/{idTopic}/{idUser}")
    public ResponseEntity<?> followAndUnfollowTopic(@PathVariable Long idTopic, @PathVariable Long idUser) {
        if (topicService.checkIfUserFollowTopic(idTopic, idUser)){
            topicService.unfollowTopico(idTopic,idUser);
            return ResponseEntity.status(200).body("Topic unfollowed successfully.");
        }
        topicService.followTopic(idTopic,idUser);
        return ResponseEntity.status(200).body("Topic followed successfully.");
    }

    @GetMapping("/{idTopic}/{idUser}")
    public ResponseEntity<Boolean> checkIfUserFollowsTopic(@PathVariable Long idTopic, @PathVariable Long idUser) {
        return ResponseEntity.status(200).body(topicService.checkIfUserFollowTopic(idTopic, idUser));
    }
}
