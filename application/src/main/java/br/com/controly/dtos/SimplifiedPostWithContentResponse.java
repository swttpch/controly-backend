package br.com.controly.dtos;

import br.com.controly.domain.entities.PostEntity;

import java.time.LocalDateTime;

public class SimplifiedPostWithContentResponse implements Comparable<SimplifiedPostWithContentResponse> {


    private Long idPost;
    private String title;
    private String content;
    private int comments;
    private SimplifiedUserResponse owner;
    private SimplifiedTopicResponse topic;
    private boolean isDoubt;
    private int points;
    private boolean userHasVoted;

    private LocalDateTime createdIn;

    public SimplifiedPostWithContentResponse(PostEntity post,
                                             SimplifiedTopicResponse topic,
                                             SimplifiedUserResponse user) {
        this.convert(post, topic, user);
    }

    public SimplifiedPostWithContentResponse(){}

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public SimplifiedUserResponse getOwner() {
        return owner;
    }

    public void setOwner(SimplifiedUserResponse owner) {
        this.owner = owner;
    }

    public SimplifiedTopicResponse getTopic() {
        return topic;
    }

    public void setTopic(SimplifiedTopicResponse topic) {
        this.topic = topic;
    }

    public boolean isDoubt() {
        return isDoubt;
    }

    public void setDoubt(boolean doubt) {
        isDoubt = doubt;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public LocalDateTime getCreatedIn() {
        return createdIn;
    }

    public void setCreatedIn(LocalDateTime createdIn) {
        this.createdIn = createdIn;
    }

    public boolean isUserHasVoted() {
        return userHasVoted;
    }

    public void setUserHasVoted(boolean userHasVoted) {
        this.userHasVoted = userHasVoted;
    }

    public SimplifiedPostWithContentResponse convert(PostEntity post,
                                                     SimplifiedTopicResponse topic,
                                                     SimplifiedUserResponse user){
        setTitle(post.getTitle());
        setOwner(user);
        setPoints(post.getPoints());
        setDoubt(post.isDoubt());
        setTopic(topic);
        if(post.getComments() == null) {
            setComments(0);
        }
        else{
            setComments(post.getComments().size());
        }
        setCreatedIn(post.getCreatedIn());
        setIdPost(post.getIdPost());
        setContent(post.getContent());
        setCreatedIn(post.getCreatedIn());
        return this;
    }

    @Override
    public int compareTo(SimplifiedPostWithContentResponse o) {
        return Integer.compare(o.points,this.points);
    }
}
