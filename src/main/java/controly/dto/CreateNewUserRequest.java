package controly.dto;

import controly.entities.UserEntity;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
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
}
