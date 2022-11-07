package controly.modules.perfilAndUsuario.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import controly.modules.pontuacao.entities.pontuacaoPostagem.PontuacaoPostagem;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
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
    @Email
    private String email;

    @OneToMany(mappedBy = "usuario") @JsonIgnore
    private Set<PontuacaoPostagem> pontuacaoPostagem = new HashSet<>();


    public UsuarioEntity(String nome, String apelido, String senha, String email) {
        this.nome = nome;
        this.apelido = apelido;
        this.senha = senha;
        this.email = email;
    }

    public UsuarioEntity(){}
}
