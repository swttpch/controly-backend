package controly.service;

import controly.entity.UserEntity;
import controly.repository.PostRepository;
import controly.repository.TopicRepository;
import controly.repository.UserRepository;
import controly.strategy.Ipostagem;
import controly.strategy.Postagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import static controly.config.Constant.IDNOTFOUND;

@Service
public class DiscussaoService implements Ipostagem {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private ValidationService validation;

    public DiscussaoService() {
    }

    @Override
    public ResponseEntity<String> enviarPostagem(Postagem discussao) {
        UserEntity userEntity = userRepository.findByIdUser(discussao.getIdUsuario()).orElseThrow();
        if (validation.existsUsuario(discussao.getIdUsuario()) || validation.existsTopico(discussao.getIdTopico()))
            return ResponseEntity.status(404).body(IDNOTFOUND);
        postRepository.save(
                discussao.converterPostagem(
                        topicRepository.findByIdTopic(discussao.getIdTopico()),
                        userEntity
                )
        );
        return ResponseEntity.status(201).body("Discussão postada.");
    }


}
