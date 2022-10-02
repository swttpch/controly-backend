package controly.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tbPontuacaoPostagem")
public class PontuacaoPostagem {
    @EmbeddedId
    private PontuacaoPostagemId id = new PontuacaoPostagemId();

    @ManyToOne
    @MapsId("idPostagem")
    private PostagemEntity postagem;

    @ManyToOne
    @MapsId("idUsuario")
    private UsuarioEntity usuario;

    private int pontuacao;

    public Long getIdPostagem(){
        return postagem.getIdPostagem();
    }

    public Long getIdUsuario(){
        return usuario.getIdUsuario();
    }

    public int getPontuacao(){
        return pontuacao;
    }

    public void setPostagem(PostagemEntity postagem) {
        this.postagem = postagem;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
}
