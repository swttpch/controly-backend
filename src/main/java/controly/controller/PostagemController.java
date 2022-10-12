package controly.controller;

import controly.controller.form.*;
import controly.model.entity.PostagemEntity;
import controly.model.service.ComentarioService;
import controly.model.service.DiscussaoService;
import controly.model.service.DuvidaService;
import controly.model.service.PostagemService;
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
    @GetMapping("")
    public ResponseEntity<List<PostagemEntity>> pegarTodasDiscussoes(){
        return postagemService.todasPostagens();
    }

    @GetMapping("/")
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

    @PutMapping("/comentario/curtir")
    public ResponseEntity<String> curtirComentario(@RequestParam Long idComentario, @RequestParam Long idUsuario) {
        return comentarioService.curtirComentario(idComentario, idUsuario);
    }
    @PutMapping("/subir")
    public ResponseEntity<String> subirPostagem(@RequestParam Long idPostagem, @RequestParam Long idUsuario){
        return postagemService.setPontuacaoPostagem(idPostagem, idUsuario, 1);
    }
    @PutMapping("/descer")
    public ResponseEntity<String> descerPostagem(@RequestParam Long idPostagem, @RequestParam Long idUsuario){
        return postagemService.setPontuacaoPostagem(idPostagem, idUsuario, -1);
    }
}
