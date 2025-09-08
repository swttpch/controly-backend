package br.com.controly.services;

import br.com.controly.dtos.CreatePostDTO;
import br.com.controly.viewmodels.SimplifiedPostWithContentViewModel;
import br.com.controly.jpa.PostRepository;
import br.com.controly.domain.entities.Comments;
import br.com.controly.domain.entities.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

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
        Posts post = postService.getPostById(idPost);
        Comments comment = commentService.getCommentById(idComment);
    }

    public SimplifiedPostWithContentViewModel createPost(CreatePostDTO newPost) {
        Posts post = postService.convertDtoToPost(newPost);
        post.initDoubt();
        postRepository.save(post);
        return postService.getSimplifiedWithContentPost(post);
    }
}
