package controly.controller;

import controly.dto.*;
import controly.entity.CommentEntity;
import controly.entity.PostPointsEntity;
import controly.entity.PostEntity;
import controly.service.DoubtService;
import controly.service.PostService;
import controly.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/posts")
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
    public ResponseEntity<List<PostagemDTO>> pegarTodasDiscussoes(){
        return postService.todasPostagens();
    }


    @GetMapping("{idPostagem}")
    public ResponseEntity<PostEntity> pegarPostagemPeloId(@PathVariable Long idPostagem){
        return postService.pegarPostagemPeloId(idPostagem);
    }

    @PutMapping("/duvida/{idDuvida}/{idComentario}")
    public ResponseEntity<String> atribuirRespostaADuvida(@PathVariable Long idDuvida, @PathVariable Long idComentario) {
        return doubtService.definirRespostaDaPostagem(idDuvida, idComentario);
    }

    @GetMapping("/comment/{idPost}")
    public ResponseEntity<List<SimplifiedCommentResponse>> getAllCommentsFromPost(@PathVariable Long idPost){
        List<SimplifiedCommentResponse> comments = commentService.getAllCommentsFromPost(idPost);
        if (comments.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(comments);
    }

    @DeleteMapping("/comentario/{idComentario}")
    public ResponseEntity<String> deleteComentario(@PathVariable Long idComentario){
        return commentService.excluirPostagem(idComentario);
    }

    @PutMapping("/postagem/subir/{idPostagem}/{idUsuario}")
    public ResponseEntity<String> subirPostagem(@PathVariable Long idPostagem, @PathVariable Long idUsuario){
        return postService.setPontuacaoPostagem(idPostagem, idUsuario, 1);
    }

    @PutMapping("/postagem/descer/{idPostagem}/{idUsuario}")
    public ResponseEntity<String> descerPostagem(@PathVariable Long idPostagem, @PathVariable Long idUsuario){
        return postService.setPontuacaoPostagem(idPostagem, idUsuario, -1);
    }

    @PutMapping("/comentario/curtir/{idComentario}/{idUsuario}")
    public ResponseEntity<String> curtirComentario(@PathVariable Long idComentario, @PathVariable Long idUsuario){
        return commentService.setPontuacaoComentario(idComentario, idUsuario);
    }

    @GetMapping("/comentario/curtir/{idComentario}/{idUsuario}")
    public ResponseEntity<Boolean> existsCurtida(@PathVariable Long idComentario, @PathVariable Long idUsuario){
        return commentService.existsCurtida(idComentario, idUsuario);
    }

    @DeleteMapping("{idPostagem}")
    public ResponseEntity<String> deletePostagem(@PathVariable Long idPostagem) {
        return postService.excluirPostagem(idPostagem);
    }

    @PutMapping("teste/{postagem}/{usuario}/{ponto}")
    public ResponseEntity<PostPointsEntity> findPontuacaoPostagem(@PathVariable Long postagem, @PathVariable Long usuario, @PathVariable int ponto){
        return postService.findPontuacaoPostagem(postagem, usuario,ponto);
    }
}
