package controly.model.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

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
    @JoinTable(name = "TopicoHasSeguidores", joinColumns =
            {@JoinColumn(name = "idTopico")}, inverseJoinColumns =
            {@JoinColumn(name= "idUsuario")})
    private List<TopicoEntity> topicosSeguidos;


    public UsuarioEntity(String nome, String apelido, String senha, String email) {
        this.nome = nome;
        this.apelido = apelido;
        this.senha = senha;
        this.email = email;
    }

    public UsuarioEntity(){

    }

    // Validar depois
    //private List<Topico> topicosQueSegue = new ArrayList<>();
}
