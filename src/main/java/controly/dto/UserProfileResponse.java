package controly.dto;

import controly.entity.TopicEntity;
import controly.entity.UserEntity;
import controly.entity.PostEntity;

import java.util.List;

public class UserProfileResponse {

    private UserEntity user;
    private List<PostEntity> posts;
    private List<TopicEntity> followedTopics;
    private PostEntity mostRatedPost;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<PostEntity> getPosts() {
        return posts;
    }

    public void setPosts(List<PostEntity> posts) {
        this.posts = posts;
    }

    public List<TopicEntity> getFollowedTopics() {
        return followedTopics;
    }

    public void setFollowedTopics(List<TopicEntity> followedTopics) {
        this.followedTopics = followedTopics;
    }

    public PostEntity getMostRatedPost() {
        return mostRatedPost;
    }

    public void setMostRatedPost(PostEntity mostRatedPost) {
        this.mostRatedPost = mostRatedPost;
    }
}
