package controly.service;


import controly.entities.UsuarioEntity;
import controly.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public ResponseEntity<UsuarioEntity> login(String email, String senha) {
        UsuarioEntity user = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Email não achado"));

        if (!user.getSenha().equals(senha))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Senha incorreta");
        return ResponseEntity.status(200).body(user);

    }
}
