package br.com.controly.dtos;

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
