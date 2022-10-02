package controly.controller;

import controly.controller.form.*;
import controly.model.entity.PontuacaoPostagem;
import controly.model.entity.PostagemEntity;
import controly.model.service.ComentarioService;
import controly.model.service.DiscussaoService;
import controly.model.service.DuvidaService;
import controly.model.service.PostagemService;
import controly.repository.ComentarioRepository;
import controly.strategy.Postar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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

    @PostMapping("/discussao")
    public ResponseEntity cadastrarDiscussao(@RequestBody Discussao post) {
        postar.setPostagem(discussaoService);
        return postar.postar(post);
    }
    @GetMapping("")
    public ResponseEntity<List<PostagemEntity>> pegarTodasDiscussoes(){
        return postagemService.todasPostagens();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostagemEntity> pegarPostagemPeloId(@PathVariable Long id){
        return postagemService.pegarPostagemPeloId(id);
    }

    @PostMapping("/duvida")
    public ResponseEntity cadastrarDuvida(@RequestBody Duvida post){
        postar.setPostagem(duvidaService);
        return postar.postar(post);
    }

    @PutMapping("/duvida/{idDuvida}/{idComentario}")
    public ResponseEntity atribuirRespostaADuvida(@PathVariable Long idDuvida, @PathVariable Long idComentario){
        return duvidaService.definirRespostaDaPostagem(idDuvida, idComentario);
    }
    @PostMapping("/comentario")
    public ResponseEntity cadastrarComentario(@RequestBody Comentario post) {
        postar.setPostagem(comentarioService);
        return postar.postar(post);
    }

//    @GetMapping("/pontuacao/{p}/{u}")
//    public ResponseEntity teste(@PathVariable Long p, @PathVariable Long u){
//        return postagemService.pegarPontuacaoPelaPostagemEUsuario(p, u);
//    }

    @PutMapping("/subir/{postagem}/{usuario}")
    public ResponseEntity subirPostagem(@PathVariable Long postagem, @PathVariable Long usuario){
        return postagemService.setPontuacaoPostagem(postagem, usuario, 1);
    }

    @PutMapping("/descer/{postagem}/{usuario}")
    public ResponseEntity descerPostagem(@PathVariable Long postagem, @PathVariable Long usuario){
        return postagemService.setPontuacaoPostagem(postagem, usuario, -1);
    }
}
