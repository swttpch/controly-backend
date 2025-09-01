package br.com.controly.viewmodels;

import br.com.controly.domain.entities.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SimplifiedUserViewModel {


    private Long idUser;
    private String name;
    private String nickname;
    private String avatar;

    private String avatarPng;

    private String about;

    public SimplifiedUserViewModel convert(UserEntity userEntity){
        SimplifiedUserViewModel simplifiedUserResponse = new SimplifiedUserViewModel();
        simplifiedUserResponse.setAbout(userEntity.getAbout());
        simplifiedUserResponse.setAvatar(userEntity.getAvatar());
        simplifiedUserResponse.setIdUser(userEntity.getIdUser());
        simplifiedUserResponse.setName(userEntity.getName());
        simplifiedUserResponse.setNickname(userEntity.getNickname());
        simplifiedUserResponse.setAvatarPng(userEntity.getAvatarPng());

        return simplifiedUserResponse;
    }
}
