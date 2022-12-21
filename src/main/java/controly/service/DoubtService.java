package controly.service;

import controly.dto.CreatePostRequest;
import controly.dto.SimplifiedPostWithContentResponse;
import controly.entity.CommentEntity;
import controly.entity.PostEntity;
import controly.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DoubtService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;

    public DoubtService() {
    }
    @Transactional
    public void setDoubtsAnswer(Long idPostagem, Long idComentario){
        PostEntity post = postService.getPostById(idPostagem);
        CommentEntity comment = commentService.getCommentById(idComentario);
        post.setAnswer(comment);
    }

    public SimplifiedPostWithContentResponse createPost(CreatePostRequest newPost) {
        PostEntity post = postService.convertDtoToPost(newPost);
        post.initDoubt();
        postRepository.save(post);
        return postService.getSimplifiedWithContentPost(post);
    }
}
