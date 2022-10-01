package controly.controller.form;

import controly.model.entity.UsuarioEntity;
import controly.repository.UsuarioRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class AtualizarUsuarioForm {

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

    public UsuarioEntity atualizar(Long id, UsuarioRepository usuarioRepository) {
        UsuarioEntity user = usuarioRepository.getReferenceById(id);
        user.setApelido(this.apelido);
        user.setEmail(this.email);
        user.setSenha(this.senha);
        user.setNome(this.nome);
        return user;
    }
}
