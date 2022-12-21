package controly.service;

import controly.dto.CreatePostRequest;
import controly.dto.SimplifiedPostWithContentResponse;
import controly.entity.PostEntity;
import controly.repository.CommentRepository;
import controly.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import static controly.config.Constant.IDNOTFOUND;

import javax.transaction.Transactional;

@Service
public class DoubtService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ValidationService validation;

    public DoubtService() {
    }



    @Transactional
    public ResponseEntity<String> definirRespostaDaPostagem(Long idPostagem, Long idComentario){
        if (validation.existsPostagem(idPostagem) || validation.existsComentario(idComentario))
            return ResponseEntity.status(404).body(IDNOTFOUND);
        postRepository.findByIdPost(idPostagem)
            .setAnswer(commentRepository.findByIdComment(idComentario));
        return ResponseEntity.status(201).body("Resposta atribuida.");
    }

    public SimplifiedPostWithContentResponse createPost(CreatePostRequest newPost) {
        PostEntity post = postService.convertDtoToPost(newPost);
        post.initDoubt();
        postRepository.save(post);
        return postService.getSimplifiedWithContentPost(post);
    }
}
