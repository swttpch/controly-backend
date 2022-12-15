package controly.dto;

import controly.entity.UserEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateNewUserRequest {

    @NotBlank
    private String name;
    @NotBlank
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
