package controly.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "tbPostagem")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbPostagem")
@Data
@SecondaryTable(name= "tbRespostaDuvida", pkJoinColumns = @PrimaryKeyJoinColumn(name = "idPostagem"))

public class PostagemEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.TABLE) @Column(name = "idPostagem")
    private Long idPostagem;

    @Column(name = "conteudo")
    private String conteudo;
    @Column(name = "criadoEm")
    private LocalDateTime criadoEm;

    @ManyToOne @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    private UsuarioEntity dono;
    @Column(name = "titulo")
    private String titulo;
    @ManyToMany
    @JoinTable(name = "postagemHasSubidas", joinColumns =
            {@JoinColumn(name = "idPostagem")}, inverseJoinColumns =
            {@JoinColumn(name= "idUsuario")})
    private List<UsuarioEntity> subidas;
    @OneToOne(cascade = CascadeType.ALL) @JoinColumn(name="idTopico", referencedColumnName = "idTopico", nullable = false)
    private TopicoEntity topico;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPostagem")
    private List<ComentarioEntity> comentarios;

    @Embedded
    private RespostaDuvidaEntity respostaDuvidaEntity;

}
