package controly.modules.topico.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import controly.modules.perfilAndUsuario.entities.UsuarioEntity;
import lombok.Data;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "tb_topico_has_seguidores")
@Data
public class TopicoHasSeguidoresEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY) @JsonIgnore
    private Long id;

    @ManyToOne(cascade=CascadeType.ALL) @JoinColumn(name = "id_usuario", referencedColumnName = "idUsuario")
    @JsonIgnore
    private UsuarioEntity usuario;

    @ManyToOne(cascade=CascadeType.ALL) @JoinColumn(name = "id_topico", referencedColumnName = "idTopico")
    private TopicoEntity topico;

}
