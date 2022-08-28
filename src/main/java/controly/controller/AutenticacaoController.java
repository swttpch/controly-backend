package controly.controller;

import controly.usuario.Autenticacao;
import controly.usuario.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("autenticacao")
public class AutenticacaoController {

    Usuario curUsuario;
    Autenticacao auth = new Autenticacao();

    @GetMapping("/{email}/{senha}")
    public String login(@PathVariable String email, @PathVariable String senha) {
        curUsuario = auth.autenticarLogin(email,senha);
        return curUsuario == null ? "Email e/ou senha invalidos" : "Login realizado com sucesso";
    }

    @GetMapping("/")
    public String getCurUsuario(){
        return curUsuario == null ? "Você não está logado" : curUsuario.toString();
    }

    @GetMapping("/logoff")
    public String logoff(){
        if (curUsuario == null) return "Você não está logado";

        curUsuario = null;
        return "Você deslogou";
    }
}
