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
    @Autowired
    ValidationService validation;
    @Override
    public ResponseEntity enviarPostagem(Postagem post) {
        if (!validation.existsUsuario(post.getIdUsuario()) || !validation.existsPostagem(post.getIdPostagem()))
            return ResponseEntity.status(404).build();
        comentarioRepository.save(
                post.converterPostagem(
                        postagemRepository.findByIdPostagem(post.getIdPostagem()),
                        usuarioRepository.findByIdUsuario(post.getIdUsuario())
                )
        );
        return ResponseEntity.status(201).build();
    }

    @Transactional
    public ResponseEntity curtirComentario(Long idComentario, Long idUsuario){
        if (!validation.existsComentario(idComentario) || !validation.existsUsuario(idUsuario))
            return ResponseEntity.status(404).build();
        ComentarioEntity comentario = comentarioRepository.findByIdComentario(idComentario);
        UsuarioEntity usuario = usuarioRepository.findByIdUsuario(idUsuario);
        if (comentario.usuarioCurtiu(usuario)){
            comentarioRepository.delete(comentario);
        } else {
            comentario.adicionarCurtida(usuario);
            comentarioRepository.save(comentario);
        }
        return  ResponseEntity.status(200).build();
    }
}
