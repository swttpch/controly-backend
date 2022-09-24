package controly.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity @Data @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class PostagemEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.TABLE) @Column(name = "idPostagem")
    private Long idPostagem;

    @Column(name = "conteudo")
    private String conteudo;
    @Column(name = "criadoEm") @Temporal(TemporalType.DATE)
    private Date criadoEm;

    @ManyToOne @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    private UsuarioEntity dono;

    public PostagemEntity(String conteudo, Date criadoEm) {
        this.conteudo = conteudo;
        this.criadoEm = criadoEm;
    }

}
