package controly.dto;

public class SimplifiedUserResponse {
    private Long idUser;
    private String name;
    private String nickname;
    private String avatar;

    private String avatarPNG;

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

    public String getAvatarPNG() {
        return avatarPNG;
    }

    public void setAvatarPNG(String avatarPNG) {
        this.avatarPNG = avatarPNG;
    }
}
