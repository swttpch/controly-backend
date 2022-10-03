package controly.model.service;

import controly.controller.form.Postagem;
import controly.model.entity.ComentarioEntity;
import controly.model.entity.PostagemEntity;
import controly.model.entity.UsuarioEntity;
import controly.repository.ComentarioRepository;
import controly.repository.PostagemRepository;
import controly.repository.UsuarioRepository;
import controly.strategy.Ipostagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService implements Ipostagem {
    @Autowired
    PostagemRepository postagemRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ComentarioRepository comentarioRepository;
    @Override
    public ResponseEntity<ComentarioEntity> enviarPostagem(Postagem post) {
        PostagemEntity postagem = postagemRepository.findById(post.getIdPostagem()).get();
        UsuarioEntity usuario = usuarioRepository.findById(post.getIdUsuario()).get();
        ComentarioEntity comentarioEntity = post.converterPostagem(postagem, usuario);
        comentarioRepository.save(comentarioEntity);
        return ResponseEntity.status(201).body(comentarioEntity);
    }
}
