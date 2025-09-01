package br.com.controly.controllers;

import br.com.controly.domain.entities.PostEntity;
import br.com.controly.dtos.*;
import br.com.controly.services.DoubtService;
import br.com.controly.services.PostService;
import br.com.controly.services.CommentService;
import br.com.controly.viewmodels.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "*")
public class PostController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private DoubtService doubtService;
    @Autowired
    private PostService postService;

    public PostController() {
    }

    @PostMapping
    public ResponseEntity<SimplifiedPostWithContentViewModel> createPost(@RequestBody @Valid CreatePostDTO newPost) {
        SimplifiedPostWithContentViewModel post = postService.createPost(newPost);
        return ResponseEntity.status(200).body(post);
    }

    @PostMapping("/doubt")
    public ResponseEntity<SimplifiedPostWithContentViewModel> createDoubt(@RequestBody @Valid CreatePostDTO newPost) {
        SimplifiedPostWithContentViewModel post = doubtService.createPost(newPost);
        return ResponseEntity.status(200).body(post);
    }

    @PostMapping("/comment")
    public ResponseEntity<SimplifiedCommentViewModel> createComment(@RequestBody @Valid CreateCommentDTO newComment) {
        SimplifiedCommentViewModel comment = commentService.createPost(newComment);
        return ResponseEntity.status(200).body(comment);
    }

    @GetMapping()
    public ResponseEntity<List<SimplifiedPostWithContentViewModel>> getAllPosts(@RequestParam(required = false) Long idUser,
                                                                                @RequestParam(required = false, defaultValue = "false") Boolean sort){
        List<SimplifiedPostWithContentViewModel> posts = new ArrayList<>();
        if(idUser==null){
           posts = postService.getAllPosts(sort);
        } else {
            posts = postService.getAllPosts(idUser,sort);
        }

        if (posts == null) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(posts);
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<PostEntity>> getAllPostsPageable(
            @RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "content") String sortBy
    ){
        Page<PostEntity> posts = postService.getAllPostsPageable(pageNo, pageSize, sortBy);
        //if (posts == null) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(posts);
    }


    @GetMapping("/{idPost}")
    public ResponseEntity<PostDetailedViewModel> getPostById(@PathVariable Long idPost){
        List<SimplifiedCommentViewModel> comments = commentService.getAllCommentsFromPost(idPost);
        PostDetailedViewModel post = postService.processGetPostByID(idPost, comments);
        return ResponseEntity.status(200).body(post);
    }

    @PutMapping("/doubt/{idDoubt}/{idComment}")
    public ResponseEntity<String> setDoubtsAnswer(@PathVariable Long idDoubt, @PathVariable Long idComment) {
        doubtService.setDoubtsAnswer(idDoubt, idComment);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/comment/{idPost}")
    public ResponseEntity<List<SimplifiedCommentViewModel>> getAllCommentsFromPost(@PathVariable Long idPost){
        List<SimplifiedCommentViewModel> comments = commentService.getAllCommentsFromPost(idPost);
        if (comments.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(comments);
    }

    @Deprecated
    @GetMapping("/comment/mobile/{idPost}/{idUser}")
    public ResponseEntity<List<LikeCommentViewModel>> getAllCommentsFromPostInMobile(@PathVariable Long idPost,
                                                                                     @PathVariable(required = false) Long idUser,
                                                                                     @RequestParam(required = false, defaultValue = "false") Boolean sort){
        List<LikeCommentViewModel> comments = commentService.getAllCommentsFromPostInMobile(idPost, idUser,sort);
        if (comments.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(comments);
    }

    @GetMapping(value = "/comment/single/{idComment}")
    public ResponseEntity<SimplifiedCommentViewModel> getSingleComment(@PathVariable Long idComment){
        SimplifiedCommentViewModel comment = commentService.getSingleComment(idComment);
        return ResponseEntity.status(200).body(comment);
    }

    @DeleteMapping("/comment/{idComment}")
    public ResponseEntity<String> deleteComment(@PathVariable Long idComment){
        if (commentService.deleteComment(idComment) != 1)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something goes wrong");
        return ResponseEntity.status(200).body("Comment deleted successfully");
    }

    @PutMapping("/rise/{idPost}/{idUser}")
    public ResponseEntity<PostPointViewModel> risePost(@PathVariable Long idPost, @PathVariable Long idUser){
        PostPointViewModel response = postService.processSetPointForPostBool(idPost, idUser);
        return ResponseEntity.status(200).body(response);
    }

    @Deprecated
    @PutMapping("/down/{idPost}/{idUser}")
    public ResponseEntity<String> downPost(@PathVariable Long idPost, @PathVariable Long idUser){
        postService.processSetPointForPost(idPost, idUser, -1);
        return ResponseEntity.status(200).body(String.format("PPoint %d set to post with id %d.", -1, idPost));
    }

    @PutMapping("/comment/like/{idComment}/{idUser}")
    public ResponseEntity<Boolean> likeComment(@PathVariable Long idComment, @PathVariable Long idUser){
        if (commentService.processLikeComment(idComment, idUser) == 1)
            return ResponseEntity.status(200).body(false);
        return ResponseEntity.status(200).body(true);
    }

    @PutMapping("/comment/like/mobile/{idComment}/{idUser}")
    public ResponseEntity<LikeCommentViewModel> likeCommentInMobile(@PathVariable Long idComment, @PathVariable Long idUser){
        LikeCommentViewModel response = new LikeCommentViewModel();
        response = commentService.processLikeCommentInMobile(idComment,idUser);

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/comment/like/{idComment}/{idUser}")
    public ResponseEntity<Boolean> checkIfCommentHasLikeByUser(@PathVariable Long idComment, @PathVariable Long idUser){
        return ResponseEntity.status(200).body(commentService.checkIfCommentHasLikeByUser(idComment, idUser));
    }

    @GetMapping("/comment/like/mobile/{idComment}/{idUser}")
    public ResponseEntity<LikeCommentViewModel> checkIfCommentHasLikeByUserInMobile(@PathVariable Long idComment,
                                                                                    @PathVariable Long idUser){
        return ResponseEntity.status(200).body(commentService.checkIfCommentHasLikeByUserInMobile(idComment, idUser));
    }

    @DeleteMapping("{idPost}")
    public ResponseEntity<?> deletePost(@PathVariable Long idPost) {
        if(postService.deletePost(idPost) != 1) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something goes wrong");
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/{idPost}/points/{idUser}")
    public ResponseEntity<Integer> getUserPointsInPost(@PathVariable Long idPost, @PathVariable Long idUser){
        return ResponseEntity.status(200).body(postService.getUserPointsInPost(idPost, idUser));
    }

    @GetMapping("/pesquisa/{idTopic}")
    public ResponseEntity searchFieldTopico(@PathVariable Long idTopic){
        List<PostEntity> postagem = postService.getTopicoForPost(idTopic);

        if (postagem.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Topico não disponivel");

        return ResponseEntity.status(HttpStatus.OK).body(postagem);
    }

    @GetMapping("/pesquisa/mobile/{idTopic}/{idUser}")
    public ResponseEntity searchFieldTopicoMobile(@PathVariable Long idTopic, @PathVariable Long idUser){
        List<SimplifiedPostWithContentViewModel> postagem = postService.getTopicoForPostMobile(idTopic, idUser);

        if (postagem.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(postagem);

        return ResponseEntity.status(HttpStatus.OK).body(postagem);
    }

    @GetMapping("/pesquisa/{idTopic}/pageable")
    public ResponseEntity searchFieldTopicPageable(@PathVariable Long idTopic){
        return null;
    }
}
