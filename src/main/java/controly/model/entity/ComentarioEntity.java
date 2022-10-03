package controly.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity @Table(name = "tbComentario") @Data @NoArgsConstructor @AllArgsConstructor
public class ComentarioEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "idComentario")
    private Long idComentario;
    @Column(name = "conteudo")
    private String conteudo;
    @Column(name = "criadoEm")
    private LocalDateTime criadoEm;

    @ManyToOne @JoinColumn(name = "idPostagem", referencedColumnName = "idPostagem")@JsonIgnore
    private PostagemEntity postagem;

    @ManyToMany
    @JoinTable(name = "tbComentarioHasCurtidas", joinColumns =
            {@JoinColumn(name = "idComentario")}, inverseJoinColumns =
            {@JoinColumn(name= "idUsuario")}) @JsonIgnore
    private List<UsuarioEntity> curtidas;

    @ManyToOne @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    private UsuarioEntity dono;

    public boolean usuarioCurtiu(UsuarioEntity usuario) {
        return curtidas.contains(usuario);
    }

    public ComentarioEntity adicionarCurtida(UsuarioEntity usuario){
        curtidas.add(usuario);
        return this;
    }

}
