package controly.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import controly.modules.perfilAndUsuario.entities.UsuarioEntity;
import controly.modules.topico.entities.TopicoEntity;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "tb_topico_has_seguidores")
public class TopicoHasSeguidoresEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY) @JsonIgnore
    private Long id;

    @ManyToOne(cascade=CascadeType.ALL) @JoinColumn(name = "id_usuario", referencedColumnName = "idUsuario")
    @JsonIgnore
    private UsuarioEntity usuario;

    @ManyToOne(cascade=CascadeType.ALL) @JoinColumn(name = "id_topico", referencedColumnName = "idTopico")
    private TopicoEntity topico;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TopicoHasSeguidoresEntity() {
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public TopicoEntity getTopico() {
        return topico;
    }

    public void setTopico(TopicoEntity topico) {
        this.topico = topico;
    }
}
