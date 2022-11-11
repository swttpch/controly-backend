package controly.perfilAndUsuario.dto;

import controly.model.entity.TopicoHasSeguidoresEntity;
import controly.postagem.entities.PostagemEntity;
import controly.perfilAndUsuario.entities.UsuarioEntity;
import lombok.Data;

import java.util.List;

@Data
public class PerfilDTO {

    private UsuarioEntity usuario;
    private List<PostagemEntity> postagens;
    private List<TopicoHasSeguidoresEntity> topicos_seguidos;


}
