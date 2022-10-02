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

import javax.transaction.Transactional;

@Service
public class ComentarioService implements Ipostagem {
    @Autowired
    PostagemRepository postagemRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ComentarioRepository comentarioRepository;
    @Override
    public ResponseEntity enviarPostagem(Postagem post) {
        PostagemEntity postagem = postagemRepository.findById(post.getIdPostagem()).get();
        UsuarioEntity usuario = usuarioRepository.findById(post.getIdUsuario()).get();
        ComentarioEntity comentarioEntity = post.converterPostagem(postagem, usuario);
        comentarioRepository.save(comentarioEntity);
        return ResponseEntity.status(201).body(comentarioEntity);
    }

    @Transactional
    public ResponseEntity curtirComentario(Long idComentario, Long idUsuario){
        ComentarioEntity comentario = comentarioRepository.findById(idComentario).get();
        UsuarioEntity usuario = usuarioRepository.findById(idUsuario).get();
        if (comentario.getCurtidas().contains(usuario)){
            comentarioRepository.delete(comentario);
            return  ResponseEntity.status(200).build();
        } else {
            comentario.getCurtidas().add(usuario);
            comentarioRepository.save(comentario);
            return  ResponseEntity.status(200).build();
        }
    }
}
