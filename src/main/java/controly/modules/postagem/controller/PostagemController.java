package controly.modules.postagem.controller;

import controly.modules.comentario.form.Comentario;
import controly.modules.comentario.entities.ComentarioEntity;
import controly.modules.postagem.dtos.PostagemDTO;
import controly.modules.postagem.pontuacao.entities.pontuacaoPostagem.PontuacaoPostagem;
import controly.modules.postagem.pontuacao.form.Discussao;
import controly.modules.postagem.pontuacao.form.Duvida;
import controly.modules.postagem.entities.PostagemEntity;
import controly.modules.postagem.service.DiscussaoService;
import controly.modules.postagem.service.DuvidaService;
import controly.modules.postagem.service.PostagemService;
import controly.modules.comentario.service.ComentarioService;
import controly.strategy.Postar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://controly.azurewebsites.net")
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
    public ResponseEntity<PostagemEntity> pegarPostagemPeloId(@PathVariable Long idPostagem){
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
    public ResponseEntity<List<ComentarioEntity>> getAllCommentsFromPost(@PathVariable Long idPostagem){
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
    public ResponseEntity<PontuacaoPostagem> findPontuacaoPostagem(@PathVariable Long postagem, @PathVariable Long usuario, @PathVariable int ponto){
        return postagemService.findPontuacaoPostagem(postagem, usuario,ponto);
    }
}
