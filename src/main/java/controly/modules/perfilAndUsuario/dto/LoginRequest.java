package controly.modules.perfilAndUsuario.dto;

import javax.validation.constraints.Email;

public class LoginRequest {
    private String email;

    private String senha;

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
