package br.com.controly.services;

import br.com.controly.dtos.CreatePostRequest;
import br.com.controly.dtos.SimplifiedPostWithContentResponse;
import br.com.controly.jpa.PostRepository;
import br.com.controly.domain.entities.CommentEntity;
import br.com.controly.domain.entities.PostEntity;
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
