package controly.modules.postagem.controller;

import controly.modules.comentario.form.Comentario;
import controly.modules.comentario.entities.ComentarioEntity;
import controly.modules.pontuacao.entities.pontuacaoPostagem.PontuacaoPostagem;
import controly.modules.pontuacao.form.Discussao;
import controly.modules.pontuacao.form.Duvida;
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
@RequestMapping("/postagens")
public class PostagemController {
    @Autowired
    final private DiscussaoService discussaoService;
    @Autowired
    final private ComentarioService comentarioService;
    @Autowired
    final private DuvidaService duvidaService;
    @Autowired
    final private PostagemService postagemService;
    @Autowired
    final private Postar postar;

    public PostagemController(DiscussaoService discussaoService, ComentarioService comentarioService, DuvidaService duvidaService, PostagemService postagemService, Postar postar) {
        this.discussaoService = discussaoService;
        this.comentarioService = comentarioService;
        this.duvidaService = duvidaService;
        this.postagemService = postagemService;
        this.postar = postar;
    }

    @PostMapping("/discussao")
    public ResponseEntity<String> cadastrarDiscussao(@RequestBody Discussao post) {
        postar.setPostagem(discussaoService);
        return postar.postar(post);
    }
    @GetMapping("/all")
    public ResponseEntity<List<PostagemEntity>> pegarTodasDiscussoes(){
        return postagemService.todasPostagens();
    }

    @GetMapping
    public ResponseEntity<PostagemEntity> pegarPostagemPeloId(@RequestParam Long idPostagem){
        return postagemService.pegarPostagemPeloId(idPostagem);
    }

    @PostMapping("/duvida")
    public ResponseEntity<String> cadastrarDuvida(@RequestBody Duvida post){
        postar.setPostagem(duvidaService);
        return postar.postar(post);
    }

    @PutMapping("/duvida")
    public ResponseEntity<String> atribuirRespostaADuvida(@RequestParam Long idDuvida, @RequestParam Long idComentario){
        return duvidaService.definirRespostaDaPostagem(idDuvida, idComentario);
    }
    @PostMapping("/comentario")
    public ResponseEntity<String> cadastrarComentario(@RequestBody Comentario post) {
        postar.setPostagem(comentarioService);
        return postar.postar(post);
    }
    @GetMapping("/comentario")
    public ResponseEntity<List<ComentarioEntity>> getAllCommentsFromPost(@RequestParam Long idPostagem){
        return comentarioService.getAllCommentsFromPost(idPostagem);
    }
    @DeleteMapping("/comentario")
    public ResponseEntity<String> deleteComentario(@RequestParam Long idComentario){
        return comentarioService.excluirPostagem(idComentario);
    }
    @PutMapping("/postagem/subir")
    public ResponseEntity<String> subirPostagem(@RequestParam Long idPostagem, @RequestParam Long idUsuario){
        return postagemService.setPontuacaoPostagem(idPostagem, idUsuario, 1);
    }
    @PutMapping("/postagem/descer")
    public ResponseEntity<String> descerPostagem(@RequestParam Long idPostagem, @RequestParam Long idUsuario){
        return postagemService.setPontuacaoPostagem(idPostagem, idUsuario, -1);
    }
    @PutMapping("/comentario/subir")
    public ResponseEntity<String> subirComentario(@RequestParam Long idComentario, @RequestParam Long idUsuario){
        return comentarioService.setPontuacaoComentario(idComentario, idUsuario, 1);
    }
    @PutMapping("/comentario/descer")
    public ResponseEntity<String> descerComentario(@RequestParam Long idComentario, @RequestParam Long idUsuario){
        return comentarioService.setPontuacaoComentario(idComentario, idUsuario, -1);
    }
    @DeleteMapping
    public ResponseEntity<String> deletePostagem(@RequestParam Long idComentario){
        return postagemService.excluirPostagem(idComentario);
    }

    @PutMapping("teste/{postagem}/{usuario}/{ponto}")
    public ResponseEntity<PontuacaoPostagem> teste(@PathVariable Long postagem, @PathVariable Long usuario, @PathVariable int ponto){
        return postagemService.findPontuacaoPostagem(postagem, usuario,ponto);
    }
}
