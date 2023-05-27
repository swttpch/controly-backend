package controly.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class PasswordRecoveryRequest {



    @NotNull
    @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
