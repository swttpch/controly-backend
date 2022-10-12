package controly.model.service;

import controly.controller.form.Postagem;
import controly.model.entity.ComentarioEntity;
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
    final private PostagemRepository postagemRepository;

    @Autowired
    final private UsuarioRepository usuarioRepository;

    @Autowired
    final private ComentarioRepository comentarioRepository;
    @Autowired
    final private ValidationService validation;

    public ComentarioService(PostagemRepository postagemRepository, UsuarioRepository usuarioRepository, ComentarioRepository comentarioRepository, ValidationService validation) {
        this.postagemRepository = postagemRepository;
        this.usuarioRepository = usuarioRepository;
        this.comentarioRepository = comentarioRepository;
        this.validation = validation;
    }

    @Override
    public ResponseEntity<String> enviarPostagem(Postagem post) {
        if (!validation.existsUsuario(post.getIdUsuario()) || !validation.existsPostagem(post.getIdPostagem()))
            return ResponseEntity.status(404).body("ID informado não existe.");
        comentarioRepository.save(
                post.converterPostagem(
                        postagemRepository.findByIdPostagem(post.getIdPostagem()),
                        usuarioRepository.findByIdUsuario(post.getIdUsuario())
                )
        );
        return ResponseEntity.status(201).body("Comentario postado.");
    }

    public ResponseEntity<String> curtirComentario(Long idComentario, Long idUsuario){
        if (!validation.existsComentario(idComentario) || !validation.existsUsuario(idUsuario))
            return ResponseEntity.status(404).body("ID informado não existe.");
        ComentarioEntity comentario = comentarioRepository.findByIdComentario(idComentario);
        UsuarioEntity usuario = usuarioRepository.findByIdUsuario(idUsuario);
        if (comentario.usuarioCurtiu(usuario)){
            comentarioRepository.delete(comentario);
            return  ResponseEntity.status(200).body("Curtida excluida.");
        } else {
            comentario.adicionarCurtida(usuario);
            comentarioRepository.save(comentario);
            return  ResponseEntity.status(200).body("Curtida atribuida.");
        }
    }
}
