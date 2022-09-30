package controly.controller;

import controly.controller.form.*;
import controly.model.entity.PostagemEntity;
import controly.model.service.ComentarioService;
import controly.model.service.DiscussaoService;
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
    private Postar postar;

    @PostMapping("/discussao")
    public ResponseEntity cadastrarDiscussao(@RequestBody Discussao post) {
        postar.setPostagem(discussaoService);
        return postar.postar(post);
    }
    @GetMapping("/discussao")
    public ResponseEntity<List<PostagemEntity>> pegarTodasDiscussoes(){
        return discussaoService.todasDiscussoes();
    }

    @PostMapping("/comentario")
    public ResponseEntity cadastrarComentario(@RequestBody Comentario post) {
        postar.setPostagem(comentarioService);
        return postar.postar(post);
    }

}
