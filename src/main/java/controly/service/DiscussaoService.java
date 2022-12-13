package controly.service;

import controly.entities.UsuarioEntity;
import controly.repository.PostagemRepository;
import controly.repository.TopicoRepository;
import controly.repository.UsuarioRepository;
import controly.strategy.Ipostagem;
import controly.strategy.Postagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import static controly.config.Constant.IDNOTFOUND;

@Service
public class DiscussaoService implements Ipostagem {
    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private ValidationService validation;

    public DiscussaoService() {
    }

    @Override
    public ResponseEntity<String> enviarPostagem(Postagem discussao) {
        UsuarioEntity usuarioEntity = usuarioRepository.findByIdUsuario(discussao.getIdUsuario()).orElseThrow();
        if (validation.existsUsuario(discussao.getIdUsuario()) || validation.existsTopico(discussao.getIdTopico()))
            return ResponseEntity.status(404).body(IDNOTFOUND);
        postagemRepository.save(
                discussao.converterPostagem(
                        topicoRepository.findByIdTopico(discussao.getIdTopico()),
                        usuarioEntity
                )
        );
        return ResponseEntity.status(201).body("Discussão postada.");
    }


}
