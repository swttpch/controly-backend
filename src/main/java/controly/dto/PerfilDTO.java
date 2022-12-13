package controly.dto;

import controly.entities.TopicoEntity;
import controly.entities.UsuarioEntity;
import controly.entities.PostagemEntity;
import lombok.Data;

import java.util.List;

@Data
public class PerfilDTO {

    private UsuarioEntity usuario;
    private List<PostagemEntity> postagens;
    private List<TopicoEntity> topicos_seguidos;
    private PostagemEntity postagemMaiorPontuacao;
}
