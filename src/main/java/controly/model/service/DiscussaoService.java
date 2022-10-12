package controly.model.service;

import controly.repository.PostagemRepository;
import controly.repository.TopicoRepository;
import controly.repository.UsuarioRepository;
import controly.strategy.Ipostagem;
import controly.controller.form.Postagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DiscussaoService implements Ipostagem {
    @Autowired
    final private PostagemRepository postagemRepository;

    @Autowired
    final private UsuarioRepository usuarioRepository;

    @Autowired
    final private TopicoRepository topicoRepository;
    @Autowired
    final private ValidationService validation;

    public DiscussaoService(PostagemRepository postagemRepository, UsuarioRepository usuarioRepository, TopicoRepository topicoRepository, ValidationService validation) {
        this.postagemRepository = postagemRepository;
        this.usuarioRepository = usuarioRepository;
        this.topicoRepository = topicoRepository;
        this.validation = validation;
    }

    @Override
    public ResponseEntity<String> enviarPostagem(Postagem discussao) {
        if (!validation.existsUsuario(discussao.getIdUsuario()) || !validation.existsTopico(discussao.getIdTopico()))
            return ResponseEntity.status(404).body("ID informado não existe.");
        postagemRepository.save(
                discussao.converterPostagem(
                        topicoRepository.findByIdTopico(discussao.getIdTopico()),
                        usuarioRepository.findByIdUsuario(discussao.getIdUsuario())
                )
        );
        return ResponseEntity.status(201).body("Discussão postada.");
    }

}
