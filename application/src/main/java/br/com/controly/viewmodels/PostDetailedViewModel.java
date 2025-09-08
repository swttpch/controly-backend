package br.com.controly.viewmodels;

import br.com.controly.domain.entities.Posts;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostDetailedViewModel {


    private Long idPost;
    private String title;
    private String content;
    private UserViewModel owner;
    private SimplifiedTopicViewModel topic;
    private List<SimplifiedCommentViewModel> comments;
    private boolean isDoubt;
    private DoubtsAnswerViewModel answer;
    private int points;
    private LocalDateTime createdIn;
}
