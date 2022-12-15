package controly.dto;

import controly.entity.TopicEntity;
import controly.entity.UserEntity;
import controly.entity.PostEntity;

import java.util.List;

public class PerfilDTO {

    private UserEntity usuario;
    private List<PostEntity> postagens;
    private List<TopicEntity> topicos_seguidos;
    private PostEntity postagemMaiorPontuacao;

    public UserEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UserEntity usuario) {
        this.usuario = usuario;
    }

    public List<PostEntity> getPostagens() {
        return postagens;
    }

    public void setPostagens(List<PostEntity> postagens) {
        this.postagens = postagens;
    }

    public List<TopicEntity> getTopicos_seguidos() {
        return topicos_seguidos;
    }

    public void setTopicos_seguidos(List<TopicEntity> topicos_seguidos) {
        this.topicos_seguidos = topicos_seguidos;
    }

    public PostEntity getPostagemMaiorPontuacao() {
        return postagemMaiorPontuacao;
    }

    public void setPostagemMaiorPontuacao(PostEntity postagemMaiorPontuacao) {
        this.postagemMaiorPontuacao = postagemMaiorPontuacao;
    }
}
