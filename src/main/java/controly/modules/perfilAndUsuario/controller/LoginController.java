package controly.modules.perfilAndUsuario.controller;

import controly.modules.perfilAndUsuario.dto.RequestLoginUsuario;
import controly.modules.perfilAndUsuario.entities.UsuarioEntity;
import controly.modules.perfilAndUsuario.service.UsuarioService;
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
    public ResponseEntity<?> getCurrentLogin(){
        if (usuario == null) {
            return  ResponseEntity.status(400).body("Não existe nenhum login ativo");
        }
        return ResponseEntity.status(200).body(usuario);
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody RequestLoginUsuario requestLoginUsuario){
        UsuarioEntity usuarioPromisse = usuarioService.login(requestLoginUsuario.getEmail(), requestLoginUsuario.getSenha());
        if (usuarioPromisse == null){
            return ResponseEntity.status(404).body("Email ou senha incorretos.");
        }
        usuario = usuarioPromisse;
        return ResponseEntity.status(200).body(usuario);
    }

    @DeleteMapping
    public ResponseEntity<?> logoff(){
        if (usuario == null) {
            return  ResponseEntity.status(400).body("Não existe nenhum login ativo");
        }
        usuario = null;
        return ResponseEntity.status(200).body("Logoff realizado.");
    }
}
