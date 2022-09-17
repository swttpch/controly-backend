package controly.controller.form;

import controly.model.EnumUsuarioStatus;
import controly.model.entity.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class CadastrarNovoUsuarioForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nome;
    @NotNull
    private String apelido;
    //private int avatar;
    @NotNull
    @Length(min=8)
    private String senha;
    @NotNull
    private String email;
    //private EnumUsuarioStatus status = EnumUsuarioStatus.ATIVO;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Usuario converter() {
        return new Usuario(nome,apelido,senha,email);
    }
}
