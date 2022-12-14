package controly.dto;

import controly.entities.TopicEntity;
import controly.entities.UserEntity;
import controly.entities.PostEntity;
import lombok.Data;

import java.util.List;

@Data
public class PerfilDTO {

    private UserEntity usuario;
    private List<PostEntity> postagens;
    private List<TopicEntity> topicos_seguidos;
    private PostEntity postagemMaiorPontuacao;
}
