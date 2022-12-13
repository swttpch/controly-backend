package controly.controller;

import controly.service.AuthService;
import controly.dto.LoginRequest;
import controly.entities.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping()
    public ResponseEntity<UsuarioEntity> login(@RequestBody LoginRequest login){
        return authService.login(login.getEmail(), login.getSenha());
    }
}
