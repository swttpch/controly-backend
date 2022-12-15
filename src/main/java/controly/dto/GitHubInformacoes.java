package controly.dto;


import controly.entities.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

public class GitHubInformacoes {

    @NotNull
    private String nome;
    @NotNull
    private String apelido;

    @NotNull
    private Long idGithub;

    @NotNull
    private String avatar;

    private String email;


    public UserEntity converter() {

        UserEntity usuario = new UserEntity();
        usuario.setName(nome);
        usuario.setAvatar(avatar);
        usuario.setNickname(apelido);
        usuario.setIdGithub(idGithub);
        usuario.setEmail(email);
        return usuario;
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

    public Long getIdGithub() {
        return idGithub;
    }

    public void setIdGithub(Long idGithub) {
        this.idGithub = idGithub;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
