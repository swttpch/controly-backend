package controly.modules.perfilAndUsuario.dto;

import controly.modules.topico.entities.TopicoHasSeguidoresEntity;
import controly.modules.perfilAndUsuario.entities.UsuarioEntity;
import controly.modules.postagem.entities.PostagemEntity;
import lombok.Data;

import java.util.List;

@Data
public class PerfilDTO {

    private UsuarioEntity usuario;
    private List<PostagemEntity> postagens;
    private List<TopicoHasSeguidoresEntity> topicos_seguidos;


}
