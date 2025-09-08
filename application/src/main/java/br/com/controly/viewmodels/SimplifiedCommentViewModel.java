package br.com.controly.viewmodels;

import br.com.controly.domain.entities.Comments;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class SimplifiedCommentViewModel {
    private Long idComment;
    private Long idPost;
    private UserViewModel owner;
    private String content;
    private int likes;
    private LocalDateTime createdIn;
}
