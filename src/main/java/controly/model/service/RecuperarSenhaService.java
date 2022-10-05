package controly.model.service;

import controly.controller.form.RecuperarSenhaForm;
import controly.model.entity.UsuarioEntity;
import controly.util.GeradorSenha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import controly.repository.UsuarioRepository;

import java.util.Optional;

@Service
public class RecuperarSenhaService {

    @Autowired
    private GeradorSenha geradorSenha;

    @Autowired
    private EmailService enviarEmail;

    @Autowired
    private UsuarioRepository usuarioRepository;


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
