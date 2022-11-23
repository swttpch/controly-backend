package controly.modules.perfilAndUsuario.dto;


import controly.modules.perfilAndUsuario.EnumRole;
import controly.modules.perfilAndUsuario.entities.UsuarioEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
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


    public UsuarioEntity converter() {

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome(nome);
        usuario.setAvatar(avatar);
        usuario.setApelido(apelido);
        usuario.setIdGithub(idGithub);
        usuario.setEmail(email);
        return usuario;
    }
}
