package controly.modules.auth.controller;

import controly.modules.auth.dtos.LoginRequest;
import controly.modules.perfilAndUsuario.entities.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "https://controly.azurewebsites.net")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping()
    public ResponseEntity<UsuarioEntity> login(@RequestBody LoginRequest login){
        return authService.login(login.getEmail(), login.getSenha());
    }
}
