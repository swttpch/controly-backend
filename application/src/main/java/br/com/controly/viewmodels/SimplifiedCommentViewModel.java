package br.com.controly.viewmodels;

import br.com.controly.domain.entities.CommentEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
public class SimplifiedCommentViewModel {
    private Long idComment;
    private Long idPost;
    private SimplifiedUserViewModel owner;
    private String content;
    private int likes;
    private LocalDateTime createdIn;

    public SimplifiedCommentViewModel(CommentEntity comment,
                                      SimplifiedUserViewModel user) {
        this.convert(comment,user);
    }
    public SimplifiedCommentViewModel convert(CommentEntity comment,
                                              SimplifiedUserViewModel user){
        setContent(comment.getContent());
        setIdComment(comment.getIdComment());
        setCreatedIn(comment.getCreatedIn());
        setOwner(user);
        setIdPost(comment.getPost().getIdPost());
        setLikes(comment.getLikes());
        return this;
    }

}
