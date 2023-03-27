package controly.controller;

import controly.dto.*;
import controly.service.DoubtService;
import controly.service.PostService;
import controly.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
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
    public ResponseEntity<List<SimplifiedPostWithContentResponse>> getAllPosts(){
        List<SimplifiedPostWithContentResponse> posts = postService.getAllPosts();
        if (posts == null) return ResponseEntity.status(204).build();
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

    @DeleteMapping("/comment/{idComment}")
    public ResponseEntity<String> deleteComment(@PathVariable Long idComment){
        if (commentService.deleteComment(idComment) != 1)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something goes wrong");
        return ResponseEntity.status(200).body("Comment deleted successfully");
    }

    @PutMapping("/rise/{idPost}/{idUser}")
    public ResponseEntity<String> risePost(@PathVariable Long idPost, @PathVariable Long idUser){
        postService.processSetPointForPost(idPost, idUser, 1);
        return ResponseEntity.status(200).body(String.format("Point %d set to post with id %d.", 1, idPost));
    }

    @PutMapping("/down/{idPost}/{idUser}")
    public ResponseEntity<String> downPost(@PathVariable Long idPost, @PathVariable Long idUser){
        postService.processSetPointForPost(idPost, idUser, -1);
        return ResponseEntity.status(200).body(String.format("PPoint %d set to post with id %d.", -1, idPost));
    }

    @PutMapping("/comment/like/{idComment}/{idUser}")
    public ResponseEntity<String> likeComment(@PathVariable Long idComment, @PathVariable Long idUser){
        if (commentService.processLikeComment(idComment, idUser) == 1)
            return ResponseEntity.status(200).body("Comment unliked successfully");
        return ResponseEntity.status(200).body("Comment liked successfully");
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
}
