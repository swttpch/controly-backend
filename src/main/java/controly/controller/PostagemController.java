package controly.controller;

import controly.controller.form.CadastrarNovaPostagemForm;
import controly.model.entity.PostagemEntity;
import controly.model.service.PostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postagens")
public class PostagemController {

    @Autowired
    private PostagemService postagemService;

    @PostMapping()
    public ResponseEntity<PostagemEntity> cadastrarUsuario(@RequestBody CadastrarNovaPostagemForm post){
        PostagemEntity postagem = postagemService.cadastrarPostagem(post);
        return ResponseEntity.status(200).body(postagem);
    }

    @GetMapping()
    public ResponseEntity<List<PostagemEntity>> buscarTodasPostagens(){
        List<PostagemEntity> listaDePostagens = postagemService.buscarTodasPostagens();
        if (listaDePostagens.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(listaDePostagens);
    }
}
