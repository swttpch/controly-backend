package controly.model.entity;

import lombok.Getter;

import javax.persistence.*;
@Entity
@Table(name = "tbPontuacaoPostagem")
@Getter
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
