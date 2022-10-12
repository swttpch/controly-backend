package controly.controller;


import controly.controller.form.CadastrarNovoUsuarioForm;
import controly.controller.form.RecuperarSenhaForm;
import controly.model.service.RecuperarSenhaService;
import controly.model.entity.UsuarioEntity;
import controly.model.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.util.List;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    final private UsuarioService usuarioService;

    @Autowired
    final private RecuperarSenhaService recuperarSenhaService;

    public UsuarioController(UsuarioService usuarioService, RecuperarSenhaService recuperarSenhaService) {
        this.usuarioService = usuarioService;
        this.recuperarSenhaService = recuperarSenhaService;
    }


    @GetMapping
    public ResponseEntity<?> getUsuario(@RequestBody Long id){
        return usuarioService.getUsuarioCadastrado(id);
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioEntity>> getListUsuarios(){
        return usuarioService.getListUsuarios();
    }

    @PostMapping()
    public ResponseEntity<String> cadastrarUsuario(@RequestBody CadastrarNovoUsuarioForm user){
        return usuarioService.cadastrarUsuario(user);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletarUsuario(@PathVariable Long id){
        return usuarioService.deletarUsuario(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarUsuario(@PathVariable Long id,
                                                   @RequestBody CadastrarNovoUsuarioForm form){
        return usuarioService.atualizarUsuario(id,form);
    }

    @PostMapping("/recuperar-senha")
    public ResponseEntity<?> recuperarSenha(@RequestBody RecuperarSenhaForm form){
        return recuperarSenhaService.recuperarSenha(form);
    }

}
