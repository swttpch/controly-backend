package controly.controller;

import controly.strategy.Comentario;
import controly.entities.CommentEntity;
import controly.dto.PostagemDTO;
import controly.entities.PostPointsEntity;
import controly.strategy.Discussao;
import controly.strategy.Duvida;
import controly.entities.PostEntity;
import controly.service.DiscussaoService;
import controly.service.DuvidaService;
import controly.service.PostagemService;
import controly.service.ComentarioService;
import controly.strategy.Postar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/postagens")
public class PostagemController {
    @Autowired
    private DiscussaoService discussaoService;
    @Autowired
    private ComentarioService comentarioService;
    @Autowired
    private DuvidaService duvidaService;
    @Autowired
    private PostagemService postagemService;
    @Autowired
    private Postar postar;

    public PostagemController() {
    }

    @PostMapping("/discussao")
    public ResponseEntity<?> cadastrarDiscussao(@RequestBody Discussao post) {
        postar.setPostagem(discussaoService);
        return postar.postar(post);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostagemDTO>> pegarTodasDiscussoes(){
        return postagemService.todasPostagens();
    }


    @GetMapping("{idPostagem}")
    public ResponseEntity<PostEntity> pegarPostagemPeloId(@PathVariable Long idPostagem){
        return postagemService.pegarPostagemPeloId(idPostagem);
    }

    @PostMapping("/duvida")
    public ResponseEntity<?> cadastrarDuvida(@RequestBody Duvida post){
        postar.setPostagem(duvidaService);
        return postar.postar(post);
    }

    @PutMapping("/duvida/{idDuvida}/{idComentario}")
    public ResponseEntity<String> atribuirRespostaADuvida(@PathVariable Long idDuvida, @PathVariable Long idComentario){
        return duvidaService.definirRespostaDaPostagem(idDuvida, idComentario);
    }

    @PostMapping("/comentario")
    public ResponseEntity<?> cadastrarComentario(@RequestBody Comentario post) {
        postar.setPostagem(comentarioService);
        return postar.postar(post);
    }

    @GetMapping("/comentario/{idPostagem}")
    public ResponseEntity<List<CommentEntity>> getAllCommentsFromPost(@PathVariable Long idPostagem){
        return comentarioService.getAllCommentsFromPost(idPostagem);
    }

    @DeleteMapping("/comentario/{idComentario}")
    public ResponseEntity<String> deleteComentario(@PathVariable Long idComentario){
        return comentarioService.excluirPostagem(idComentario);
    }

    @PutMapping("/postagem/subir/{idPostagem}/{idUsuario}")
    public ResponseEntity<String> subirPostagem(@PathVariable Long idPostagem, @PathVariable Long idUsuario){
        return postagemService.setPontuacaoPostagem(idPostagem, idUsuario, 1);
    }

    @PutMapping("/postagem/descer/{idPostagem}/{idUsuario}")
    public ResponseEntity<String> descerPostagem(@PathVariable Long idPostagem, @PathVariable Long idUsuario){
        return postagemService.setPontuacaoPostagem(idPostagem, idUsuario, -1);
    }

    @PutMapping("/comentario/curtir/{idComentario}/{idUsuario}")
    public ResponseEntity<String> curtirComentario(@PathVariable Long idComentario, @PathVariable Long idUsuario){
        return comentarioService.setPontuacaoComentario(idComentario, idUsuario);
    }

    @GetMapping("/comentario/curtir/{idComentario}/{idUsuario}")
    public ResponseEntity<Boolean> existsCurtida(@PathVariable Long idComentario, @PathVariable Long idUsuario){
        return comentarioService.existsCurtida(idComentario, idUsuario);
    }

    @DeleteMapping("{idPostagem}")
    public ResponseEntity<String> deletePostagem(@PathVariable Long idPostagem) {
        return postagemService.excluirPostagem(idPostagem);
    }

    @PutMapping("teste/{postagem}/{usuario}/{ponto}")
    public ResponseEntity<PostPointsEntity> findPontuacaoPostagem(@PathVariable Long postagem, @PathVariable Long usuario, @PathVariable int ponto){
        return postagemService.findPontuacaoPostagem(postagem, usuario,ponto);
    }
}
