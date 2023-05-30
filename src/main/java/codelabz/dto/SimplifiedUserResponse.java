package codelabz.dto;

import codelabz.entity.UserEntity;

public class SimplifiedUserResponse {


    private Long idUser;
    private String name;
    private String nickname;
    private String avatar;

    private String avatarPng;

    private String about;

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatarPng() {
        return avatarPng;
    }

    public void setAvatarPng(String avatarPng) {
        this.avatarPng = avatarPng;
    }

    public SimplifiedUserResponse convert(UserEntity userEntity){
        SimplifiedUserResponse simplifiedUserResponse = new SimplifiedUserResponse();
        simplifiedUserResponse.setAbout(userEntity.getAbout());
        simplifiedUserResponse.setAvatar(userEntity.getAvatar());
        simplifiedUserResponse.setIdUser(userEntity.getIdUser());
        simplifiedUserResponse.setName(userEntity.getName());
        simplifiedUserResponse.setNickname(userEntity.getNickname());
        simplifiedUserResponse.setAvatarPng(userEntity.getAvatarPng());

        return simplifiedUserResponse;
    }
}
