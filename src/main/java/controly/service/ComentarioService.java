package controly.service;

import controly.entities.UserEntity;
import controly.strategy.Postagem;
import controly.entities.CommentEntity;
import controly.entities.CommentPointsEntity;
import controly.repository.CommentRepository;
import controly.repository.CommentPointsRepository;
import controly.repository.PostRepository;
import controly.repository.UserRepository;
import controly.strategy.Ipostagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

import static controly.config.Constant.IDNOTFOUND;

@Service
public class ComentarioService implements Ipostagem {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ValidationService validation;
    @Autowired
    private CommentPointsRepository commentPointsRepository;

    public ComentarioService() {
    }

    @Override
    public ResponseEntity<String> enviarPostagem(Postagem post) {
        UserEntity userEntity = userRepository.findByIdUser(post.getIdUsuario()).orElseThrow();
        if (validation.existsUsuario(post.getIdUsuario()) || validation.existsPostagem(post.getIdPostagem()))
            return ResponseEntity.status(404).body(IDNOTFOUND);
        commentRepository.save(
                post.converterComentario(
                        postRepository.findByIdPost(post.getIdPostagem()),
                        userEntity
                )
        );
        return ResponseEntity.status(201).body("Comentario postado.");
    }

    public ResponseEntity<String> excluirPostagem(Long idComentario) {
        if (validation.existsComentario(idComentario))
            return ResponseEntity.status(404).body(IDNOTFOUND);

        commentRepository.delete(
                commentRepository.findByIdComment(idComentario)
        );
        return ResponseEntity.status(200).body("Comentário excluido.");
    }
    @Transactional
    public ResponseEntity<String> setPontuacaoComentario(Long comentario, Long usuario){
        if (
                validation.existsComentario(comentario) ||
                        validation.existsUsuario(usuario)
        ) return ResponseEntity.status(404).body(IDNOTFOUND);
        CommentEntity comentario1 = commentRepository.findByIdComment(comentario);
        UserEntity userEntity = userRepository.findByIdUser(usuario).orElseThrow();
        if (!commentPointsRepository.existByCommentAndUser(comentario, usuario).isPresent()){
            commentPointsRepository.save(
                    new CommentPointsEntity()
                            .setComment(comentario1)
                            .setUser(userEntity)
            );
            return ResponseEntity.status(200).body("Curtida criada");
        } else {
            commentPointsRepository.deleteByComment_idComment(comentario,usuario);
            return ResponseEntity.status(200).body("Curtida excluida");
        }
    }

    public ResponseEntity<Boolean> existsCurtida(Long comentario, Long usuario){
        if (
                validation.existsComentario(comentario) ||
                        validation.existsUsuario(usuario)
        ) return ResponseEntity.status(404).build();
        if (!commentPointsRepository.existByCommentAndUser(comentario, usuario).isPresent()){

            return ResponseEntity.status(200).body(false);
        } else {
            return ResponseEntity.status(200).body(true);
        }
    }

    public ResponseEntity<List<CommentEntity>> getAllCommentsFromPost(Long idPostagem) {
        if (
                validation.existsPostagem(idPostagem)
        ) return ResponseEntity.status(404).build();
        List<CommentEntity> comentarios = commentRepository.findByPostIdPost(idPostagem);
        if (comentarios.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(comentarios);
    }
}
