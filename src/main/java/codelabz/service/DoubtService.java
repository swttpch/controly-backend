package codelabz.service;

import codelabz.dto.CreatePostRequest;
import codelabz.dto.SimplifiedPostWithContentResponse;
import codelabz.entity.CommentEntity;
import codelabz.entity.PostEntity;
import codelabz.repository.PostRepository;
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
    public void setDoubtsAnswer(Long idPost, Long idComment){
        PostEntity post = postService.getPostById(idPost);
        CommentEntity comment = commentService.getCommentById(idComment);
        post.setAnswer(comment);
    }

    public SimplifiedPostWithContentResponse createPost(CreatePostRequest newPost) {
        PostEntity post = postService.convertDtoToPost(newPost);
        post.initDoubt();
        postRepository.save(post);
        return postService.getSimplifiedWithContentPost(post);
    }
}
