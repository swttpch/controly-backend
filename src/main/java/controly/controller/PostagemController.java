package controly.controller;

import controly.controller.form.CadastrarNovaPostagemForm;
import controly.controller.form.CadastrarNovoUsuarioForm;
import controly.model.entity.PostagemEntity;
import controly.model.service.PostagemService;
import controly.model.service.UsuarioService;
import controly.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postagens")
public class PostagemController {

    private final PostagemService postagemService = new PostagemService();
    /*
    @PostMapping()
    public ResponseEntity<PostagemEntity> cadastrarUsuario(@RequestBody CadastrarNovaPostagemForm post){
        return postagemService.cadastrarPostagem(post);
    }
    */
}
