package br.com.controly.dtos;


import br.com.controly.domain.entities.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
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

    public UserEntity convert() {
        UserEntity user = new UserEntity();
        user.setName(name);
        user.setAvatar(avatar);
        user.setAvatarPng(avatar);
        user.setNickname(nickname);
        user.setIdGithub(idGithub);
        user.setEmail(email);
        return user;
    }
}
