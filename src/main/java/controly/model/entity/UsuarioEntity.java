package controly.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="tb_usuario")
@Data
public class UsuarioEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name= "idUsuario")
    private Long idUsuario;
    @NotNull
    private String nome;
    @NotNull
    private String apelido;
    private int avatar;
    @NotNull
    @Length(min=8)
    private String senha;
    @NotNull
    private String email;

    @ManyToMany
    @JoinTable(name = "tbTopicoHasSeguidores", joinColumns =
            {@JoinColumn(name = "idTopico")}, inverseJoinColumns =
            {@JoinColumn(name= "idUsuario")})
    private List<TopicoEntity> topicosSeguidos;

    @OneToMany(mappedBy = "usuario") @JsonIgnore
    private Set<PontuacaoPostagem> pontuacaoPostagem = new HashSet<>();
}
