package br.com.controly.dtos;


import br.com.controly.domain.entities.Users;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class GithubUserDTO {
    @NotNull
    private String name;
    @NotNull
    private String nickname;

    @NotNull
    private Long idGithub;

    @NotNull
    private String avatar;

    private String email;

    public Users convert() {
        Users user = new Users();
        user.setName(name);

        user.setNickname(nickname);
        user.setEmail(email);
        return user;
    }
}
