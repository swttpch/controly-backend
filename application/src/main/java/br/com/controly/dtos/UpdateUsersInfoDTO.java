package br.com.controly.dtos;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateUsersInfoDTO {



    private String name;

    private String nickname;

    private String about;

    private String password;

    private String avatar;

    private String avatarPng;

    private String email;

}
