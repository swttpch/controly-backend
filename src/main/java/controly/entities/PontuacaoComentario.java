package controly.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "tbPontuacaoComentario")
public class PontuacaoComentario {
    @EmbeddedId
    final private PontuacaoComentarioId id = new PontuacaoComentarioId();

    @ManyToOne(cascade=CascadeType.ALL)
    @MapsId("idComentario") @JoinColumn(name = "idComentario")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ComentarioEntity comentario;

    @ManyToOne
    @MapsId("idUsuario") @JoinColumn(name = "idUsuario")
    private UsuarioEntity usuario;


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
}
