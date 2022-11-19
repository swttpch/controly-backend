package controly.modules.postagem.pontuacao.entities.pontuacaoPostagem;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PontuacaoPostagemId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long idPostagem;
    private Long idUsuario;

    public PontuacaoPostagemId(){

    }

    public PontuacaoPostagemId(Long idPostagem, Long idUsuario){
        super();
        this.idPostagem = idPostagem;
        this.idUsuario = idUsuario;
    }

    public Long getIdPostagem() {
        return idPostagem;
    }

    public void setIdPostagem(Long idPostagem) {
        this.idPostagem = idPostagem;
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
                + ((idPostagem == null) ? 0 : idPostagem.hashCode());
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
        PontuacaoPostagemId other = (PontuacaoPostagemId) obj;
        return Objects.equals(getIdPostagem(), other.getIdPostagem()) && Objects.equals(getIdUsuario(), other.getIdUsuario());
    }
}
