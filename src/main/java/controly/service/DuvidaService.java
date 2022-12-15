package controly.service;

import controly.entity.UserEntity;
import controly.entity.PostEntity;
import controly.strategy.Postagem;
import controly.repository.CommentRepository;
import controly.repository.PostRepository;
import controly.repository.TopicRepository;
import controly.repository.UserRepository;
import controly.strategy.Ipostagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import static controly.config.Constant.IDNOTFOUND;

import javax.transaction.Transactional;

@Service
public class DuvidaService implements Ipostagem {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ValidationService validation;

    public DuvidaService() {
    }


    @Override
    @Transactional
    public ResponseEntity<String> enviarPostagem(Postagem duvida) {
        UserEntity userEntity = userRepository.findByIdUser(duvida.getIdUsuario()).orElseThrow();
        if (validation.existsTopico(duvida.getIdTopico()) || validation.existsUsuario(duvida.getIdUsuario()))
            return ResponseEntity.status(404).body(IDNOTFOUND);

        PostEntity postagem =  duvida.converterPostagem(
                topicRepository.findByIdTopic(duvida.getIdTopico()),
                userEntity
        ).initDoubt();

        postRepository.save(
                postagem
        );
        return ResponseEntity.status(201).body("Duvida postada.");
    }

    @Transactional
    public ResponseEntity<String> definirRespostaDaPostagem(Long idPostagem, Long idComentario){
        if (validation.existsPostagem(idPostagem) || validation.existsComentario(idComentario))
            return ResponseEntity.status(404).body(IDNOTFOUND);
        postRepository.findByIdPost(idPostagem)
            .setAnswer(commentRepository.findByIdComment(idComentario));
        return ResponseEntity.status(201).body("Resposta atribuida.");
    }
}
