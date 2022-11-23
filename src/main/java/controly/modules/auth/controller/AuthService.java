package controly.modules.auth.controller;

import controly.modules.auth.dtos.LoginRequest;
import controly.modules.perfilAndUsuario.entities.UsuarioEntity;
import controly.modules.perfilAndUsuario.repository.UsuarioRepository;
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

    public ResponseEntity<?> login(LoginRequest login) {

        Optional<UsuarioEntity> usuario = usuarioRepository.findByEmail(login.getEmail());

        if(usuario.isPresent()){
            if(usuario.get().getSenha().equals(login.getSenha())){
                return ResponseEntity.status(200).body(200);
            }
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Senha incorreta");
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Email não achado");


    }
}
