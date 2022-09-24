package controly.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "tbDuvida")
public class DuvidaEntity extends DiscussaoEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idResposta", referencedColumnName = "idComentario")
    private ComentarioEntity resposta;

    public DuvidaEntity(String conteudo, Date criadoEm, String titulo, TopicoEntity topico) {
        super(conteudo, criadoEm, titulo, topico);
    }

    public ComentarioEntity getResposta() {
        return resposta;
    }

    public void setResposta(ComentarioEntity resposta) {
        this.resposta = resposta;
    }
}
