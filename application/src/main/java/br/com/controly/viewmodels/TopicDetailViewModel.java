package br.com.controly.viewmodels;

import br.com.controly.domain.entities.TopicEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
public class TopicDetailViewModel {

    private Long idTopic = new TopicEntity().getIdTopic();

    private String name = new TopicEntity().getName();

    private String about = new TopicEntity().getAbout();
    private LocalDate createdAt = new TopicEntity().getCreatedAt();
    private String svg = new TopicEntity().getSvg();

    private String png = new TopicEntity().getPng();

    private Integer countFollowers;
    private Boolean userHasFollowed;
}
