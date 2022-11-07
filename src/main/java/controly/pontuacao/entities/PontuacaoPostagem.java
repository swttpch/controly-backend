package controly.pontuacao.entities;

import controly.perfilAndUsuario.entities.UsuarioEntity;
import controly.postagem.entities.PostagemEntity;

import javax.persistence.*;
@Entity
@Table(name = "tbPontuacaoPostagem")
public class PontuacaoPostagem {
    @EmbeddedId
    final private PontuacaoPostagemId id = new PontuacaoPostagemId();

    @ManyToOne(cascade=CascadeType.ALL)
    @MapsId("idPostagem") @JoinColumn(name = "idPostagem")
    private PostagemEntity postagem;

    @ManyToOne(cascade=CascadeType.ALL)
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
