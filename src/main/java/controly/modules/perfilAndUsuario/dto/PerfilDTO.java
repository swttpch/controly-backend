package controly.modules.perfilAndUsuario.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import controly.modules.topico.entities.TopicoEntity;
import controly.modules.topico.entities.TopicoHasSeguidoresEntity;
import controly.modules.perfilAndUsuario.entities.UsuarioEntity;
import controly.modules.postagem.entities.PostagemEntity;
import lombok.Data;

import java.util.Comparator;
import java.util.List;

@Data
public class PerfilDTO {

    private UsuarioEntity usuario;
    private List<PostagemEntity> postagens;
    private List<TopicoEntity> topicos_seguidos;
    @JsonProperty
    public PostagemEntity getPostagemMaisPontuada(){
        return postagens.stream().max(Comparator.comparing(PostagemEntity::getPontuacao)).orElse(null);
    }
}
