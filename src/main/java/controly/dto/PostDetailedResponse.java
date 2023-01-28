package controly.dto;

import controly.entity.PostEntity;

import java.time.LocalDateTime;
import java.util.List;

public class PostDetailedResponse {
    private Long idPost;
    private String title;
    private String content;
    private SimplifiedUserResponse owner;
    private SimplifiedTopicResponse topic;
    private List<SimplifiedCommentResponse> comments;
    private boolean isDoubt;
    private DoubtsAnswerResponse answer;
    private int points;
    private LocalDateTime createdIn;

    public PostDetailedResponse(PostEntity post,
                                SimplifiedTopicResponse topic,
                                SimplifiedUserResponse user,
                                List<SimplifiedCommentResponse> comments) {
        convertPost(post,topic,user,comments);
    }

    public PostDetailedResponse(PostEntity post,
                                SimplifiedTopicResponse topic,
                                SimplifiedUserResponse user,
                                List<SimplifiedCommentResponse> comments,
                                DoubtsAnswerResponse answer) {
        convertDoubt(post,topic,user,comments,answer);
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

    public List<SimplifiedCommentResponse> getComments() {
        return comments;
    }

    public void setComments(List<SimplifiedCommentResponse> comments) {
        this.comments = comments;
    }

    public boolean isDoubt() {
        return isDoubt;
    }

    public void setDoubt(boolean doubt) {
        isDoubt = doubt;
    }

    public DoubtsAnswerResponse getAnswer() {
        return answer;
    }

    public void setAnswer(DoubtsAnswerResponse answer) {
        this.answer = answer;
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

    public PostDetailedResponse convertDoubt(PostEntity post,
                                        SimplifiedTopicResponse topic,
                                        SimplifiedUserResponse user,
                                         List<SimplifiedCommentResponse> comments,
                                         DoubtsAnswerResponse answer){
        setTitle(post.getTitle());
        setOwner(user);
        setPoints(post.getPoints());
        setDoubt(post.isDoubt());
        setAnswer(answer);
        setTopic(topic);
        setIdPost(post.getIdPost());
        setContent(post.getContent());
        setComments(comments);
        setCreatedIn(post.getCreatedIn());
        return this;
    }

    public PostDetailedResponse convertPost(PostEntity post,
                                             SimplifiedTopicResponse topic,
                                             SimplifiedUserResponse user,
                                            List<SimplifiedCommentResponse> comments){
        setTitle(post.getTitle());
        setOwner(user);
        setPoints(post.getPoints());
        setDoubt(post.isDoubt());
        setTopic(topic);
        setIdPost(post.getIdPost());
        setContent(post.getContent());
        setComments(comments);
        setCreatedIn(post.getCreatedIn());
        return this;
    }

}
