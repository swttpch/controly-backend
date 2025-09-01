package br.com.controly.dtos;

import java.util.List;

public class UserProfileResponse {



    private SimplifiedUserResponse user;
    private List<SimplifiedPostResponse> posts;
    private List<SimplifiedTopicResponse> followedTopics;
    private SimplifiedPostResponse mostRatedPost;

    public SimplifiedUserResponse getUser() {
        return user;
    }

    public void setUser(SimplifiedUserResponse user) {
        this.user = user;
    }

    public List<SimplifiedPostResponse> getPosts() {
        return posts;
    }

    public void setPosts(List<SimplifiedPostResponse> posts) {
        this.posts = posts;
    }

    public List<SimplifiedTopicResponse> getFollowedTopics() {
        return followedTopics;
    }

    public void setFollowedTopics(List<SimplifiedTopicResponse> followedTopics) {
        this.followedTopics = followedTopics;
    }

    public SimplifiedPostResponse getMostRatedPost() {
        return mostRatedPost;
    }

    public void setMostRatedPost(SimplifiedPostResponse mostRatedPost) {
        this.mostRatedPost = mostRatedPost;
    }
}
