package controly.model.entity;

import javax.persistence.*;
import java.util.Date;

@Embeddable
public class RespostaDuvidaEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idResposta", referencedColumnName = "idComentario", table = "tbRespostaDuvida")
    private ComentarioEntity resposta;
    @Column(name = "resolvido",  table = "tbRespostaDuvida")
    private boolean resolvido;

    @Column(name = "resolvidoEm", table = "tbRespostaDuvida")
    private Date resolvidoEm;

    public RespostaDuvidaEntity(boolean resolvido) {
        this.resolvido = false;
    }

    public ComentarioEntity getResposta() {
        return resposta;
    }

    public void setResposta(ComentarioEntity resposta) {
        this.resposta = resposta;
    }
}
