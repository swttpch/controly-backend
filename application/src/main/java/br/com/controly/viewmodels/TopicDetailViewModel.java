package br.com.controly.viewmodels;

import br.com.controly.domain.entities.Topics;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TopicDetailViewModel {

    private Long idTopic = new Topics().getId();

    private String name = new Topics().getName();

    private String about = new Topics().getAbout();
    private LocalDateTime createdAt = new Topics().getCreatedAt();

    private Integer countFollowers;
    private Boolean userHasFollowed;
}
