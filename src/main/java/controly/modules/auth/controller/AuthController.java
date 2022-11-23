package controly.modules.auth.controller;

import controly.modules.auth.dtos.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest login){
        return authService.login(login);
    }
}
