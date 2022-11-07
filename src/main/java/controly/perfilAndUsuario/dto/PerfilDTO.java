package controly.perfilAndUsuario.dto;

import controly.postagem.entities.PostagemEntity;
import controly.topico.entities.TopicoEntity;
import controly.perfilAndUsuario.entities.UsuarioEntity;
import lombok.Data;

import java.util.List;

@Data
public class PerfilDTO {

    private UsuarioEntity usuario;
    private List<PostagemEntity> postagens;
    private List<TopicoEntity> topicos_seguidos;


}
