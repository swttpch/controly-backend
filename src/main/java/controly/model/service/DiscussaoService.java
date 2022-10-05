package controly.model.service;

import controly.model.entity.PostagemEntity;
import controly.model.entity.TopicoEntity;
import controly.model.entity.UsuarioEntity;
import controly.repository.PostagemRepository;
import controly.repository.TopicoRepository;
import controly.repository.UsuarioRepository;
import controly.controller.form.Discussao;
import controly.strategy.Ipostagem;
import controly.controller.form.Postagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DiscussaoService implements Ipostagem {
    @Autowired
    PostagemRepository postagemRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TopicoRepository topicoRepository;
    @Autowired
    ValidationService validation;

    @Override
    public ResponseEntity enviarPostagem(Postagem discussao) {
        if (!validation.existsUsuario(discussao.getIdUsuario()) || !validation.existsTopico(discussao.getIdTopico()))
            return ResponseEntity.status(404).build();
        postagemRepository.save(
                discussao.converterPostagem(
                        topicoRepository.findByIdTopico(discussao.getIdTopico()),
                        usuarioRepository.findByIdUsuario(discussao.getIdUsuario())
                )
        );
        return ResponseEntity.status(201).build();
    }

}
