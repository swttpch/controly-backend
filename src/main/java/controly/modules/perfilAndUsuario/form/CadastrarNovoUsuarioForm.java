package controly.modules.perfilAndUsuario.form;

import controly.modules.perfilAndUsuario.EnumRole;
import controly.modules.perfilAndUsuario.entities.UsuarioEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class CadastrarNovoUsuarioForm {


    @NotNull
    private String nome;
    @NotNull
    private String apelido;
    @NotNull
    @Length(min=8)
    private String senha;
    @NotNull
    @Email
    private String email;
    private EnumRole role;

    private String descricao;

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

    public EnumRole getRole() {
        return role;
    }

    public void setRole(EnumRole role) {
        this.role = role;
    }

    public UsuarioEntity converter() {

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setNome(nome);
        usuario.setAvatar("1");
        usuario.setApelido(apelido);

        return usuario;
    }
}
