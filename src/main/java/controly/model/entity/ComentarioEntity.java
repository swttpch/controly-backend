package controly.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity @Table(name = "tbComentario") @Data
public class ComentarioEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "idComentario")
    private Long idComentario;
    @Column(name = "conteudo")
    private String conteudo;
    @Column(name = "criadoEm")
    private Date criadoEm;

    @ManyToMany
    @JoinTable(name = "comentarioHasCurtidas", joinColumns =
            {@JoinColumn(name = "idComentario")}, inverseJoinColumns =
            {@JoinColumn(name= "idUsuario")})
    private List<UsuarioEntity> curtidas;

    @ManyToOne @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    private UsuarioEntity dono;

    public ComentarioEntity(String conteudo, Date criadoEm) {
        this.conteudo = conteudo;
        this.criadoEm = criadoEm;
    }
}
