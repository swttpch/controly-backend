package controly.model;

import controly.controller.form.CadastrarNovoUsuarioForm;

public class ValidacaoUsuario {

    public String validar(CadastrarNovoUsuarioForm form){

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

    public Boolean validaEmail(CadastrarNovoUsuarioForm form){

        String email = form.getEmail();
        return email.contains("@") && email.length() > 10 && email.contains(".");
    }

    public Boolean validaSenha(CadastrarNovoUsuarioForm form){

        String senha = form.getSenha();
        return senha.length()>=8;
    }


}
