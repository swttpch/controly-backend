package controly.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "tb_topico_has_seguidores")
@Data
public class TopicoHasSeguidoresEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "idUsuario")
    @JsonIgnore
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "id_topico", referencedColumnName = "idTopico")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TopicoEntity topico;

}
