package br.com.controly.viewmodels;

import br.com.controly.domain.entities.Posts;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SimplifiedPostViewModel {
    private Long idPost;
    private String title;
    private UserViewModel owner;
    private SimplifiedTopicViewModel topic;
    private boolean isDoubt;
    private int points;
}
