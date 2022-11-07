package controly.pontuacao.entities;

import controly.comentario.entities.ComentarioEntity;
import controly.perfilAndUsuario.entities.UsuarioEntity;

import javax.persistence.*;

@Entity
@Table(name = "tbPontuacaoComentario")
public class PontuacaoComentario {
    @EmbeddedId
    final private PontuacaoComentarioId id = new PontuacaoComentarioId();

    @ManyToOne(cascade=CascadeType.ALL)
    @MapsId("idComentario") @JoinColumn(name = "idComentario")
    private ComentarioEntity comentario;

    @ManyToOne(cascade=CascadeType.ALL)
    @MapsId("idUsuario") @JoinColumn(name = "idUsuario")
    private UsuarioEntity usuario;

    private int pontuacao;


    public ComentarioEntity getComentario() {
        return comentario;
    }

    public PontuacaoComentario setComentario(ComentarioEntity comentario) {
        this.comentario = comentario;
        return this;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public PontuacaoComentario setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
        return this;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public PontuacaoComentario setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
        return this;
    }
}
