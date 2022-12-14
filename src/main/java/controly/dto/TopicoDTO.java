package controly.dto;

import controly.entities.TopicEntity;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
public class TopicoDTO {

    @Id
    private Long idTopico = new TopicEntity().getIdTopic();

    @NotNull
    private String nome = new TopicEntity().getName();

    @NotNull
    private String descricao = new TopicEntity().getAbout();

    private Integer seguidores;

}
