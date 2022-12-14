package controly.dto;

import controly.entities.UserEntity;
import controly.entities.PostEntity;
import controly.entities.TopicEntity;
import lombok.Data;

import javax.persistence.Id;

@Data
public class PostagemDTO {

    @Id
    private Long idPostagem = new PostEntity().getIdPost();

    private String titulo = new PostEntity().getTitle();

    private String conteudo = new PostEntity().getContent();

    private UserEntity dono = new PostEntity().getOwner();

    private TopicEntity topico = new PostEntity().getTopic();

    private Integer pontuacaoPostagem = new PostEntity().getPoints();
}
