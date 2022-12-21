package controly.service;

import controly.dto.*;
import controly.entity.PostEntity;
import controly.entity.UserEntity;
import controly.entity.CommentEntity;
import controly.entity.CommentPointsEntity;
import controly.repository.CommentRepository;
import controly.repository.CommentPointsRepository;
import controly.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static controly.config.Constant.IDNOTFOUND;

@Service
public class CommentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ValidationService validation;
    @Autowired
    private CommentPointsRepository commentPointsRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    public CommentService() {
    }

    public SimplifiedCommentResponse getSimplifiedComment(CommentEntity comment){
        if (comment==null) return null;
        SimplifiedUserResponse user = userService.getSimplifiedUser(comment.getOwner());
        return new SimplifiedCommentResponse(comment, user);
    }
    @Transactional
    public SimplifiedCommentResponse createPost(CreateCommentRequest newPost) {
        PostEntity post = postService.getPostById(newPost.getIdPost());
        UserEntity user = userService.getUserById(newPost.getIdUser());
        CommentEntity comment = commentRepository.save(newPost.convert(post,user));
        return getSimplifiedComment(comment);
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

    public List<SimplifiedCommentResponse> getAllCommentsFromPost(Long idPostagem) {
        PostEntity post = postService.getPostById(idPostagem);
        List<CommentEntity> commentEntities = commentRepository.findByPost(post);
        List<SimplifiedCommentResponse> simplifiedComments =
                commentEntities.stream()
                        .map(this::getSimplifiedComment).collect(Collectors.toList());
        return simplifiedComments;
    }
}
