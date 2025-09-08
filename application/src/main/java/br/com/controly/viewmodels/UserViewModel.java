package br.com.controly.viewmodels;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserViewModel {
    private Long idUser;
    private String name;
    private String nickname;
    private String avatar;
    private String avatarPng;
    private String about;
}
