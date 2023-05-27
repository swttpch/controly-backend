package controly.dto;

import controly.entity.CommentEntity;
import controly.entity.PostEntity;
import controly.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CreateCommentRequest {


    @NotNull
    private String content;
    @NotNull
    private Long idUser;

    @NotNull
    private Long idPost;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }

    public CommentEntity convert(PostEntity post, UserEntity user){
        CommentEntity comment = new CommentEntity();
        comment.setOwner(user);
        comment.setContent(content);
        comment.setPost(post);
        comment.setCreatedIn(LocalDateTime.now());
        return comment;
    }
}
