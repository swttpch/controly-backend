package controly.dto;


import controly.entities.UserEntity;

import javax.validation.constraints.NotNull;

public class GithubUserRequest {

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
        user.setNickname(nickname);
        user.setIdGithub(idGithub);
        user.setEmail(email);
        return user;
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
