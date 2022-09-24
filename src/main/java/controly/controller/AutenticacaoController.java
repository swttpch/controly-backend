package controly.controller;

import controly.security.Autenticacao;
import controly.model.entity.UsuarioEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {

    UsuarioEntity curUsuarioEntity;
    Autenticacao auth = new Autenticacao();

    @GetMapping("/{email}/{senha}")
    public String login(@PathVariable String email, @PathVariable String senha) {
        curUsuarioEntity = auth.autenticarLogin(email,senha);
        return curUsuarioEntity == null ? "Email e/ou senha invalidos" : "Login realizado com sucesso";
    }

    @GetMapping("/")
    public String getCurUsuario(){
        return curUsuarioEntity == null ? "Você não está logado" : curUsuarioEntity.toString();
    }

    @GetMapping("/logoff")
    public String logoff(){
        if (curUsuarioEntity == null) return "Você não está logado";

        curUsuarioEntity = null;
        return "Você deslogou";
    }
}
