package controly.service;


import controly.entities.UserEntity;
import controly.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<UserEntity> login(String email, String senha) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Email não achado"));

        if (!user.getPassword().equals(senha))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Senha incorreta");
        return ResponseEntity.status(200).body(user);

    }
}
