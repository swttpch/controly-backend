package controly.controller;

import controly.controller.form.CadastrarNovaPostagemForm;
import controly.controller.form.CadastrarNovoUsuarioForm;
import controly.model.entity.PostagemEntity;
import controly.model.service.PostagemService;
import controly.model.service.UsuarioService;
import controly.repository.PostagemRepository;
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
        return postagemService.cadastrarPostagem(post);
    }

    @GetMapping()
    public ResponseEntity<List<PostagemEntity>> buscarTodasPostagens(){
        // do something
        return null;
    }
}
