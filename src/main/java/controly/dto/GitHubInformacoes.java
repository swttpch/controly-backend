package controly.dto;


import controly.entities.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
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
}
