package br.com.controly.viewmodels;

import br.com.controly.domain.entities.Posts;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class SimplifiedPostWithContentViewModel implements Comparable<SimplifiedPostWithContentViewModel> {


    private Long idPost;
    private String title;
    private String content;
    private int comments;
    private UserViewModel owner;
    private SimplifiedTopicViewModel topic;
    private boolean isDoubt;
    private int points;
    private boolean userHasVoted;

    private LocalDateTime createdIn;

    @Override
    public int compareTo(SimplifiedPostWithContentViewModel o) {
        return Integer.compare(o.points,this.points);
    }
}
