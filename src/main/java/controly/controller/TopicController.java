package controly.controller;

import controly.dto.SimplifiedTopicResponse;
import controly.dto.TopicDetailResponse;
import controly.entity.TopicEntity;
import controly.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


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

    @GetMapping("/{idTopic}")
    public ResponseEntity<TopicDetailResponse> getTopicById(@PathVariable long idTopic){
        TopicDetailResponse topic = topicService.getTopicDetailedFromTopicEntity(topicService.getTopicById(idTopic));
        return ResponseEntity.status(200).body(topic);
    }

    @GetMapping
    public ResponseEntity<Page<TopicEntity>> getAllTopics(
            @RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(defaultValue = "1") Integer pageSize,
            @RequestParam(defaultValue = "name") String sortBy){

        Page<TopicEntity> topics = topicService.getAllTopics(pageNo, pageSize, sortBy);
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

    @GetMapping("/user/{idUser}")
    public ResponseEntity<List<SimplifiedTopicResponse>> getTopicsByUser(@PathVariable Long idUser){
        List<SimplifiedTopicResponse> topics = topicService.getTopicsUserFollows(idUser);
        return ResponseEntity.status(200).body(topics);
    }
}
