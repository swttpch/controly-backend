package codelabz.dto;

import codelabz.entity.CommentEntity;

import java.time.LocalDateTime;

public class SimplifiedCommentResponse {


    private Long idComment;
    private Long idPost;
    private SimplifiedUserResponse owner;
    private String content;
private int likes;

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    private LocalDateTime createdIn;

    public SimplifiedCommentResponse(CommentEntity comment,
                                     SimplifiedUserResponse user) {
        this.convert(comment,user);
    }

    public LocalDateTime getCreatedIn() {
        return createdIn;
    }

    public void setCreatedIn(LocalDateTime createdIn) {
        this.createdIn = createdIn;
    }

    public Long getIdComment() {
        return idComment;
    }

    public void setIdComment(Long idComment) {
        this.idComment = idComment;
    }

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }

    public SimplifiedUserResponse getOwner() {
        return owner;
    }

    public void setOwner(SimplifiedUserResponse owner) {
        this.owner = owner;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public SimplifiedCommentResponse convert(CommentEntity comment,
                                             SimplifiedUserResponse user){
        setContent(comment.getContent());
        setIdComment(comment.getIdComment());
        setCreatedIn(comment.getCreatedIn());
        setOwner(user);
        setIdPost(comment.getPost().getIdPost());
        setLikes(comment.getLikes());
        return this;
    }

}
