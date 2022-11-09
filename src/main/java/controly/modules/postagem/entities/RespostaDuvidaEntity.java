package controly.modules.postagem.entities;

import controly.modules.comentario.entities.ComentarioEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RespostaDuvidaEntity {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idResposta", referencedColumnName = "idComentario", table = "tbRespostaDuvida")
    private ComentarioEntity resposta;
    @Column(name = "resolvido",  table = "tbRespostaDuvida")
    private boolean resolvido;

    @Column(name = "resolvidoEm", table = "tbRespostaDuvida")
    private LocalDateTime resolvidoEm;

    public RespostaDuvidaEntity setResolvido(boolean resolvido){
        this.resolvido = resolvido;
        return this;
    }

    public RespostaDuvidaEntity setResposta(ComentarioEntity resposta){
        this.resposta = resposta;
        return this;
    }
}
