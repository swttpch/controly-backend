package br.com.controly.dtos;

import br.com.controly.domain.entities.UserEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateNewUserRequest {



    @NotBlank
    private String name;

    private String nickname;
    @NotNull
    @Size(min=8)
    private String password;
    @NotNull
    @Email
    private String email;

    public UserEntity convert() {
        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setAvatar("1");
        user.setAvatarPng("https://raw.githubusercontent.com/codelabzproject/public/main/img/avatar1.png");
        user.setNickname(nickname);
        return user;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
