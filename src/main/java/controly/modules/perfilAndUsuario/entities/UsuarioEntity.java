package controly.modules.perfilAndUsuario.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import controly.modules.topico.entities.TopicoEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
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

    private String avatar;

    @JsonIgnore
    @Length(min=8)
    private String senha;

    @Email
    private String email;
    @JsonIgnore
    private Boolean isAtivo=true;

    @JsonIgnore
    private Long idGithub;

    private String descricao;

    public UsuarioEntity(String nome, String apelido, String senha, String email) {
        this.nome = nome;
        this.apelido = apelido;
        this.senha =senha;
        this.email = email;
        this.isAtivo = true;
    }

    public UsuarioEntity(){}

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAtivo() {
        return isAtivo;
    }

    public void setAtivo(Boolean ativo) {
        isAtivo = ativo;
    }


}
