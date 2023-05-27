package controly.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name="tbUser")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name= "idUser")
    private Long idUser;
    @NotNull
    private String name;
    @NotNull
    private String nickname;

    private String avatar;

    private String avatarPng;

    @JsonIgnore
    private String password;

    private String email;
    @JsonIgnore
    private Boolean isActive =true;

    @JsonIgnore
    private Long idGithub;

    private String about = "";
    @Column(name = "disabledIn")
    private LocalDateTime disabledIn;

    private String token;

    public UserEntity(String name, String nickname, String password, String email) {
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.isActive = true;
    }


    public UserEntity() {
    }
@JsonIgnore
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
        return this.avatarPng;
    }

    public void setAvatarPng(String avatarPng) {
        this.avatarPng = avatarPng;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Long getIdGithub() {
        return idGithub;
    }

    public void setIdGithub(Long idGithub) {
        this.idGithub = idGithub;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public LocalDateTime getDisabledIn() {
        return disabledIn;
    }

    public void setDisabledIn(LocalDateTime disabledIn) {
        this.disabledIn = disabledIn;
    }

    public int disableUser() {
        if (!isActive) return 0;
        setActive(false);
        setDisabledIn(LocalDateTime.now());
        return 1;
    }

    public int enableUser() {
        if (isActive) return 0;
        setActive(true);
        setDisabledIn(null);
        return 1;
    }


}
