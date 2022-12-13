package controly.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PontuacaoComentarioId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long idComentario;
    private Long idUsuario;

    public PontuacaoComentarioId(){

    }

    public PontuacaoComentarioId(Long idComentario, Long idUsuario){
        super();
        this.idComentario = idComentario;
        this.idUsuario = idUsuario;
    }

    public Long getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Long idComentario) {
        this.idComentario = idComentario;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idComentario == null) ? 0 : idComentario.hashCode());
        result = prime * result
                + ((idUsuario == null) ? 0 : idUsuario.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PontuacaoComentarioId other = (PontuacaoComentarioId) obj;
        return Objects.equals(getIdComentario(), other.getIdComentario()) && Objects.equals(getIdUsuario(), other.getIdUsuario());
    }
}
