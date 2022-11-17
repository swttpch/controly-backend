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
import org.springframework.security.access.prepost.PreAuthorize;
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

    public PostagemController() {
    }

    @PreAuthorize("hasAnyRole('ADM')")
    @PostMapping("/discussao")
    public ResponseEntity<String> cadastrarDiscussao(@RequestBody Discussao post) {
        postar.setPostagem(discussaoService);
        return postar.postar(post);
    }

    @PreAuthorize("hasAnyRole('ADM')")
    @GetMapping("/all")
    public ResponseEntity<List<PostagemEntity>> pegarTodasDiscussoes(){
        return postagemService.todasPostagens();
    }


    @PreAuthorize("hasAnyRole('ADM')")
    @GetMapping("{idPostagem}")
    public ResponseEntity<PostagemEntity> pegarPostagemPeloId(@PathVariable Long idPostagem){
        return postagemService.pegarPostagemPeloId(idPostagem);
    }

    @PreAuthorize("hasAnyRole('ADM')")
    @PostMapping("/duvida")
    public ResponseEntity<String> cadastrarDuvida(@RequestBody Duvida post){
        postar.setPostagem(duvidaService);
        return postar.postar(post);
    }

    @PreAuthorize("hasAnyRole('ADM')")
    @PutMapping("/duvida")
    public ResponseEntity<String> atribuirRespostaADuvida(@RequestParam Long idDuvida, @RequestParam Long idComentario){
        return duvidaService.definirRespostaDaPostagem(idDuvida, idComentario);
    }

    @PreAuthorize("hasAnyRole('ADM')")
    @PostMapping("/comentario")
    public ResponseEntity<String> cadastrarComentario(@RequestBody Comentario post) {
        postar.setPostagem(comentarioService);
        return postar.postar(post);
    }

    @PreAuthorize("hasAnyRole('ADM')")
    @GetMapping("/comentario/{idPostagem}")
    public ResponseEntity<List<ComentarioEntity>> getAllCommentsFromPost(@PathVariable Long idPostagem){
        return comentarioService.getAllCommentsFromPost(idPostagem);
    }

    @PreAuthorize("hasAnyRole('ADM')")
    @DeleteMapping("/comentario/{idComentario}")
    public ResponseEntity<String> deleteComentario(@PathVariable Long idComentario){
        return comentarioService.excluirPostagem(idComentario);
    }

    @PreAuthorize("hasAnyRole('ADM')")
    @PutMapping("/postagem/subir/{idPostagem}/{idUsuario}")
    public ResponseEntity<String> subirPostagem(@PathVariable Long idPostagem, @PathVariable Long idUsuario){
        return postagemService.setPontuacaoPostagem(idPostagem, idUsuario, 1);
    }

    @PreAuthorize("hasAnyRole('ADM')")
    @PutMapping("/postagem/descer/{idPostagem}/{idUsuario}")
    public ResponseEntity<String> descerPostagem(@PathVariable Long idPostagem, @PathVariable Long idUsuario){
        return postagemService.setPontuacaoPostagem(idPostagem, idUsuario, -1);
    }

    @PreAuthorize("hasAnyRole('ADM')")
    @PutMapping("/comentario/subir")
    public ResponseEntity<String> subirComentario(@RequestParam Long idComentario, @RequestParam Long idUsuario){
        return comentarioService.setPontuacaoComentario(idComentario, idUsuario, 1);
    }

    @PreAuthorize("hasAnyRole('ADM')")
    @PutMapping("/comentario/descer")
    public ResponseEntity<String> descerComentario(@RequestParam Long idComentario, @RequestParam Long idUsuario){
        return comentarioService.setPontuacaoComentario(idComentario, idUsuario, -1);
    }

    @PreAuthorize("hasAnyRole('ADM')")
    @DeleteMapping
    public ResponseEntity<String> deletePostagem(@RequestParam Long idPostagem) {
        return postagemService.excluirPostagem(idPostagem);
    }

    @PreAuthorize("hasAnyRole('ADM')")
    @PutMapping("teste/{postagem}/{usuario}/{ponto}")
    public ResponseEntity<PontuacaoPostagem> teste(@PathVariable Long postagem, @PathVariable Long usuario, @PathVariable int ponto){
        return postagemService.findPontuacaoPostagem(postagem, usuario,ponto);
    }
}
