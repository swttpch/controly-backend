package controly.service;

import controly.dto.RecuperarSenhaForm;
import controly.entities.UsuarioEntity;
import controly.util.GeradorSenha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import controly.repository.UsuarioRepository;

import java.util.Optional;

@Service
public class RecuperarSenhaService {

    @Autowired
    final private GeradorSenha geradorSenha;

    @Autowired
    final private EmailService enviarEmail;

    @Autowired
    final private UsuarioRepository usuarioRepository;

    public RecuperarSenhaService(GeradorSenha geradorSenha, EmailService enviarEmail, UsuarioRepository usuarioRepository) {
        this.geradorSenha = geradorSenha;
        this.enviarEmail = enviarEmail;
        this.usuarioRepository = usuarioRepository;
    }


    public ResponseEntity<?> recuperarSenha(RecuperarSenhaForm usuario){

        Optional<UsuarioEntity> usuarioEncotrado = usuarioRepository.findByEmail(usuario.getEmail());

        if(usuarioEncotrado.isPresent()){

            String novaSenha = geradorSenha.gerarSenha();
            usuarioEncotrado.get().setSenha(novaSenha);
            usuarioRepository.save(usuarioEncotrado.get());

            return enviarEmail.sendEmail(usuarioEncotrado.get().getNome(),usuarioEncotrado.get().getEmail(),novaSenha);

        }

        return ResponseEntity.status(404).build();
    }
}
