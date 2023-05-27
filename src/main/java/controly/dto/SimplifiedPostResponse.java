package controly.dto;

import controly.entity.PostEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimplifiedPostResponse {


    private Long idPost;
    private String title;
    private SimplifiedUserResponse owner;
    private SimplifiedTopicResponse topic;
    private boolean isDoubt;
    private int points;

    public SimplifiedPostResponse(PostEntity post,
                                  SimplifiedTopicResponse topic,
                                  SimplifiedUserResponse user) {
        this.convert(post, topic, user);
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

    public SimplifiedUserResponse getOwner() {
        return owner;
    }

    public void setOwner(SimplifiedUserResponse owner) {
        this.owner = owner;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public SimplifiedPostResponse convert(PostEntity post,
                                          SimplifiedTopicResponse topic,
                                          SimplifiedUserResponse user){
        setTitle(post.getTitle());
        setOwner(user);
        setPoints(post.getPoints());
        setDoubt(post.isDoubt());
        setTopic(topic);
        setIdPost(post.getIdPost());
        return this;
    }
}
