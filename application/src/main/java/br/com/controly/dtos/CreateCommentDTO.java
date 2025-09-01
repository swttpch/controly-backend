package br.com.controly.dtos;

import br.com.controly.domain.entities.CommentEntity;
import br.com.controly.domain.entities.PostEntity;
import br.com.controly.domain.entities.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class CreateCommentDTO {
    @NotNull
    private String content;
    @NotNull
    private Long idUser;

    @NotNull
    private Long idPost;

    public CommentEntity convert(PostEntity post, UserEntity user){
        CommentEntity comment = new CommentEntity();
        comment.setOwner(user);
        comment.setContent(content);
        comment.setPost(post);
        comment.setCreatedIn(LocalDateTime.now());
        return comment;
    }
}
