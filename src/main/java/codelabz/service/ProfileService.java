package codelabz.service;

import codelabz.dto.UserProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ProfileService {
    @Autowired
    private TopicService topicService;
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    public UserProfileResponse getUserProfile(Long id){
        UserProfileResponse userProfile = new UserProfileResponse();
        userProfile.setUser(
                userService.getSimplifiedUser(userService.getUserById(id))
        );
        userProfile.setPosts(
                postService.getPostByUserId(id)
                        .stream()
                        .map(post -> postService.getSimplifiedPost(post))
                        .collect(Collectors.toList())
        );
        userProfile.setFollowedTopics(
                topicService.getAllTopicsByUser(id)
                        .stream()
                        .map(topic-> topicService.getSimplifiedTopic(topic))
                        .collect(Collectors.toList())
        );
        userProfile.setMostRatedPost(
                postService.getSimplifiedPost(postService.getPostMostRatedByUserId(id))
        );
        return userProfile;
    }

}
