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

    @Override
    public ResponseEntity enviarPostagem(Postagem discussao) {
        TopicoEntity topico = topicoRepository.findById(discussao.getIdTopico()).get();
        UsuarioEntity usuario = usuarioRepository.findById(discussao.getIdUsuario()).get();
        PostagemEntity postagemEntity = discussao.converterPostagem(topico, usuario);
        postagemRepository.save(postagemEntity);
        return ResponseEntity.status(201).body(postagemEntity);
    }

    public ResponseEntity<List<PostagemEntity>> todasDiscussoes() {
        List<PostagemEntity> postagemEntities = postagemRepository.findAll();
        return ResponseEntity.status(200).body(postagemEntities);
    }
}
