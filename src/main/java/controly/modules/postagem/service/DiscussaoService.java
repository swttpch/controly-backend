package controly.modules.postagem.service;

import controly.modules.perfilAndUsuario.entities.UsuarioEntity;
import controly.service.ValidationService;
import controly.modules.postagem.repository.PostagemRepository;
import controly.modules.topico.repository.TopicoRepository;
import controly.modules.perfilAndUsuario.repository.UsuarioRepository;
import controly.strategy.Ipostagem;
import controly.modules.postagem.entities.Postagem;
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
