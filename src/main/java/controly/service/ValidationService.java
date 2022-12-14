package controly.service;

import controly.repository.CommentRepository;
import controly.repository.UserRepository;
import controly.repository.CommentPointsRepository;
import controly.repository.PostPointsRepository;
import controly.repository.PostRepository;
import controly.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {
    @Autowired
    final private PostRepository postRepository;
    @Autowired
    final private CommentRepository commentRepository;
    @Autowired
    final private UserRepository userRepository;
    @Autowired
    final private TopicRepository topicRepository;
    @Autowired
    final private PostPointsRepository postPointsRepository;

    @Autowired
    final private CommentPointsRepository commentPointsRepository;

    public ValidationService(PostRepository postRepository, CommentRepository commentRepository, UserRepository userRepository, TopicRepository topicRepository, PostPointsRepository postPointsRepository, CommentPointsRepository commentPointsRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
        this.postPointsRepository = postPointsRepository;
        this.commentPointsRepository = commentPointsRepository;
    }


    public boolean existsUsuario(Long idUsuario){
        return userRepository.findById(idUsuario).isEmpty();
    }

    public boolean existsPostagem(Long idPostagem){
        return postRepository.findById(idPostagem).isEmpty();
    }


    public boolean existsComentario(Long idComentario){
        return commentRepository.findById(idComentario).isEmpty();
    }

    public boolean existsTopico(Long idTopico){
        return topicRepository.findById(idTopico).isEmpty();
    }


    public boolean existsPontuacaoWithPostagemAndUsuario(Long idPostagem, Long idUsuario) {
        return postPointsRepository.existByPostAndUser(idPostagem, idUsuario).isPresent();
    }

}
