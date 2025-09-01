package br.com.controly.viewmodels;

import br.com.controly.domain.entities.PostEntity;

import java.time.LocalDateTime;
import java.util.List;

public class PostDetailedViewModel {


    private Long idPost;
    private String title;
    private String content;
    private SimplifiedUserViewModel owner;
    private SimplifiedTopicViewModel topic;
    private List<SimplifiedCommentViewModel> comments;
    private boolean isDoubt;
    private DoubtsAnswerViewModel answer;
    private int points;
    private LocalDateTime createdIn;

    public PostDetailedViewModel(PostEntity post,
                                 SimplifiedTopicViewModel topic,
                                 SimplifiedUserViewModel user,
                                 List<SimplifiedCommentViewModel> comments) {
        convertPost(post,topic,user,comments);
    }

    public PostDetailedViewModel(PostEntity post,
                                 SimplifiedTopicViewModel topic,
                                 SimplifiedUserViewModel user,
                                 List<SimplifiedCommentViewModel> comments,
                                 DoubtsAnswerViewModel answer) {
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

    public SimplifiedUserViewModel getOwner() {
        return owner;
    }

    public void setOwner(SimplifiedUserViewModel owner) {
        this.owner = owner;
    }

    public SimplifiedTopicViewModel getTopic() {
        return topic;
    }

    public void setTopic(SimplifiedTopicViewModel topic) {
        this.topic = topic;
    }

    public List<SimplifiedCommentViewModel> getComments() {
        return comments;
    }

    public void setComments(List<SimplifiedCommentViewModel> comments) {
        this.comments = comments;
    }

    public boolean isDoubt() {
        return isDoubt;
    }

    public void setDoubt(boolean doubt) {
        isDoubt = doubt;
    }

    public DoubtsAnswerViewModel getAnswer() {
        return answer;
    }

    public void setAnswer(DoubtsAnswerViewModel answer) {
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

    public PostDetailedViewModel convertDoubt(PostEntity post,
                                              SimplifiedTopicViewModel topic,
                                              SimplifiedUserViewModel user,
                                              List<SimplifiedCommentViewModel> comments,
                                              DoubtsAnswerViewModel answer){
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

    public PostDetailedViewModel convertPost(PostEntity post,
                                             SimplifiedTopicViewModel topic,
                                             SimplifiedUserViewModel user,
                                             List<SimplifiedCommentViewModel> comments){
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
