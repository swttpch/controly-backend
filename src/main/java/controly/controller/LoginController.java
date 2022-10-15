package controly.controller;

import controly.model.entity.UsuarioEntity;
import controly.model.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private final UsuarioService usuarioService;
    private UsuarioEntity usuario;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @GetMapping
    public ResponseEntity getCurrentLogin(){
        if (usuario == null) {
            return  ResponseEntity.status(400).body("Não existe nenhum login ativo");
        }
        return ResponseEntity.status(200).body(usuario);
    }

    @PostMapping
    public ResponseEntity login(@RequestParam String email, @RequestParam String senha){
        UsuarioEntity usuarioPromisse = usuarioService.login(email, senha);
        if (usuarioPromisse == null){
            return ResponseEntity.status(404).body("Email ou senha incorretos.");
        }
        usuario = usuarioPromisse;
        return ResponseEntity.status(200).body(usuario);
    }

    @DeleteMapping
    public ResponseEntity logoff(){
        if (usuario == null) {
            return  ResponseEntity.status(400).body("Não existe nenhum login ativo");
        }
        usuario = null;
        return ResponseEntity.status(200).body("Logoff realizado.");
    }
}
