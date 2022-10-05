package controly.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class RecuperarSenhaForm {

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
