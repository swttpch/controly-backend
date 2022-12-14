package controly;

import controly.dto.CreateNewUserRequest;

public class ValidacaoUsuario {

    public String validar(CreateNewUserRequest form){

        if(!validaEmail(form)){
            return "Email Inválido";
        }
        if(!validaSenha(form)){
            return "Senha Inválida";
        }
        /*
        if(!validarGenero(form)){
            return "Gênero Inválido";
        }
        */
        return null;
    }

    public Boolean validaEmail(CreateNewUserRequest form){

        String email = form.getEmail();
        return email.contains("@") && email.length() > 10 && email.contains(".");
    }

    public Boolean validaSenha(CreateNewUserRequest form){

        String senha = form.getPassword();
        return senha.length()>=8;
    }


}
