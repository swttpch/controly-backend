package controly.dto;

import controly.entity.PostEntity;

public class SimplifiedPostWithContentResponse {
    private Long idPost;
    private String title;
    private String content;
    private SimplifiedUserResponse owner;
    private SimplifiedTopicResponse topic;
    private boolean isDoubt;
    private int points;

    public SimplifiedPostWithContentResponse(PostEntity post,
                                  SimplifiedTopicResponse topic,
                                  SimplifiedUserResponse user) {
        this.convert(post, topic, user);
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

    public SimplifiedPostWithContentResponse convert(PostEntity post,
                                          SimplifiedTopicResponse topic,
                                          SimplifiedUserResponse user){
        setTitle(post.getTitle());
        setOwner(user);
        setPoints(post.getPoints());
        setDoubt(post.isDoubt());
        setTopic(topic);
        setIdPost(post.getIdPost());
        setContent(post.getContent());
        return this;
    }
}
