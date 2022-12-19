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
@CrossOrigin(origins = "*")
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    public TopicController() {
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDetailResponse> getTopicById(@PathVariable long id){
        TopicDetailResponse topic = topicService.getTopicDetailedFromTopicEntity(topicService.getTopicById(id));
        return ResponseEntity.status(200).body(topic);
    }

    @GetMapping
    public ResponseEntity<List<TopicDetailResponse>> getAllTopics(){
        List<TopicEntity> topics = topicService.getAllTopics();
        if (topics.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Didn't fould any topics");
        List<TopicDetailResponse> topicDetailResponseList =
                topics.stream()
                        .map(topic-> topicService.getTopicDetailedFromTopicEntity(topic))
                        .collect(Collectors.toList());
        return ResponseEntity.status(200).body(topicDetailResponseList);
    }

    @PostMapping("/{idTopico}/{idUsuario}")
    public ResponseEntity<?> followTopico(@PathVariable Long idTopico, @PathVariable Long idUsuario) {
        return topicService.followTopico(idTopico, idUsuario);
    }

    @DeleteMapping("/{idTopico}/{idUsuario}")
    public ResponseEntity<?> unfollowTopico(@PathVariable Long idTopico, @PathVariable Long idUsuario) {
        return topicService.unfollowTopico(idTopico, idUsuario);
    }

    @PostMapping
    public ResponseEntity<TopicEntity> postTopicos(@RequestBody TopicEntity topicEntity) {
        return topicService.postTopicos(topicEntity);
    }

    @GetMapping("/{idTopico}/{idUsuario}")
    public ResponseEntity<Boolean> userFollowTopico(@PathVariable Long idTopico, @PathVariable Long idUsuario) {
        return topicService.userFollowTopic(idTopico, idUsuario);
    }
}
