package controly.modules.postagem.pontuacao.entities.pontuacaoPostagem;

import controly.modules.perfilAndUsuario.entities.UsuarioEntity;
import controly.modules.postagem.entities.PostagemEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
@Entity
@Table(name = "tbPontuacaoPostagem")
public class PontuacaoPostagem {
    @EmbeddedId
    final private PontuacaoPostagemId id = new PontuacaoPostagemId();

    @ManyToOne(cascade=CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @MapsId("idPostagem") @JoinColumn(name = "idPostagem")
    private PostagemEntity postagem;

    @ManyToOne
    @MapsId("idUsuario") @JoinColumn(name = "idUsuario")
    private UsuarioEntity usuario;

    private int pontuacao;

    public PostagemEntity getPostagem() {
        return postagem;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public PontuacaoPostagem setPostagem(PostagemEntity postagem) {
        this.postagem = postagem;
        return this;
    }

    public PontuacaoPostagem setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
        return this;
    }

    public PontuacaoPostagem setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
        return this;
    }
}
