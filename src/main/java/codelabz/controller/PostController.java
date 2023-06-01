package codelabz.controller;

import codelabz.dto.*;
import codelabz.entity.PostEntity;
import codelabz.service.DoubtService;
import codelabz.service.PostService;
import codelabz.service.CommentService;
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
    public ResponseEntity<SimplifiedPostWithContentResponse> createPost(@RequestBody @Valid CreatePostRequest newPost) {
        SimplifiedPostWithContentResponse post = postService.createPost(newPost);
        return ResponseEntity.status(200).body(post);
    }

    @PostMapping("/doubt")
    public ResponseEntity<SimplifiedPostWithContentResponse> createDoubt(@RequestBody @Valid CreatePostRequest newPost) {
        SimplifiedPostWithContentResponse post = doubtService.createPost(newPost);
        return ResponseEntity.status(200).body(post);
    }

    @PostMapping("/comment")
    public ResponseEntity<SimplifiedCommentResponse> createComment(@RequestBody @Valid CreateCommentRequest newComment) {
        SimplifiedCommentResponse comment = commentService.createPost(newComment);
        return ResponseEntity.status(200).body(comment);
    }

    @GetMapping()
    public ResponseEntity<List<SimplifiedPostWithContentResponse>> getAllPosts(@RequestParam(required = false) Long idUser,
                                                                               @RequestParam(required = false, defaultValue = "false") Boolean sort){
        List<SimplifiedPostWithContentResponse> posts = new ArrayList<>();
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
    public ResponseEntity<PostDetailedResponse> getPostById(@PathVariable Long idPost){
        List<SimplifiedCommentResponse> comments = commentService.getAllCommentsFromPost(idPost);
        PostDetailedResponse post = postService.processGetPostByID(idPost, comments);
        return ResponseEntity.status(200).body(post);
    }

    @PutMapping("/doubt/{idDoubt}/{idComment}")
    public ResponseEntity<String> setDoubtsAnswer(@PathVariable Long idDoubt, @PathVariable Long idComment) {
        doubtService.setDoubtsAnswer(idDoubt, idComment);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/comment/{idPost}")
    public ResponseEntity<List<SimplifiedCommentResponse>> getAllCommentsFromPost(@PathVariable Long idPost){
        List<SimplifiedCommentResponse> comments = commentService.getAllCommentsFromPost(idPost);
        if (comments.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(comments);
    }

    @GetMapping(value = "/comment/single/{idComment}")
    public ResponseEntity<SimplifiedCommentResponse> getSingleComment(@PathVariable Long idComment){
        SimplifiedCommentResponse comment = commentService.getSingleComment(idComment);
        return ResponseEntity.status(200).body(comment);
    }

    @DeleteMapping("/comment/{idComment}")
    public ResponseEntity<String> deleteComment(@PathVariable Long idComment){
        if (commentService.deleteComment(idComment) != 1)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something goes wrong");
        return ResponseEntity.status(200).body("Comment deleted successfully");
    }

    @PutMapping("/rise/{idPost}/{idUser}")
    public ResponseEntity<PostPointResponse> risePost(@PathVariable Long idPost, @PathVariable Long idUser){
        PostPointResponse response = postService.processSetPointForPostBool(idPost, idUser);
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

    @GetMapping("/comment/like/{idComment}/{idUser}")
    public ResponseEntity<Boolean> checkIfCommentHasLikeByUser(@PathVariable Long idComment, @PathVariable Long idUser){
        return ResponseEntity.status(200).body(commentService.checkIfCommentHasLikeByUser(idComment, idUser));
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

    @GetMapping("/pesquisa/{idTopic}/pageable")
    public ResponseEntity searchFieldTopicPageable(@PathVariable Long idTopic){
        return null;
    }
}
