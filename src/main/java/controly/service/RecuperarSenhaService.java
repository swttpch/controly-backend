package controly.service;

import controly.dto.RecuperarSenhaForm;
import controly.entities.UserEntity;
import controly.util.GeradorSenha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import controly.repository.UserRepository;

import java.util.Optional;

@Service
public class RecuperarSenhaService {

    @Autowired
    final private GeradorSenha geradorSenha;

    @Autowired
    final private EmailService enviarEmail;

    @Autowired
    final private UserRepository userRepository;

    public RecuperarSenhaService(GeradorSenha geradorSenha, EmailService enviarEmail, UserRepository userRepository) {
        this.geradorSenha = geradorSenha;
        this.enviarEmail = enviarEmail;
        this.userRepository = userRepository;
    }


    public ResponseEntity<?> recuperarSenha(RecuperarSenhaForm usuario){

        Optional<UserEntity> usuarioEncotrado = userRepository.findByEmail(usuario.getEmail());

        if(usuarioEncotrado.isPresent()){

            String novaSenha = geradorSenha.gerarSenha();
            usuarioEncotrado.get().setPassword(novaSenha);
            userRepository.save(usuarioEncotrado.get());

            return enviarEmail.sendEmail(usuarioEncotrado.get().getName(),usuarioEncotrado.get().getEmail(),novaSenha);

        }

        return ResponseEntity.status(404).build();
    }
}
