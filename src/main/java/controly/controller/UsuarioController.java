package controly.controller;


import controly.controller.dto.UsuarioCadastradoDTO;
import controly.controller.form.AtualizarUsuarioForm;
import controly.controller.form.CadastrarNovoUsuarioForm;
import controly.model.service.UsuarioService;
import controly.security.Autenticacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    Autenticacao auth = new Autenticacao();

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<UsuarioCadastradoDTO> getUsuarios(){

        // Criar?

        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioCadastradoDTO> getUsuario(@PathVariable Long id){
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<String> cadastrarUsuario(@RequestBody CadastrarNovoUsuarioForm user){
        return usuarioService.cadastrarUsuario(user);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<?> deletarUsuario(@RequestBody Long id){
        return usuarioService.deletarUsuario(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UsuarioCadastradoDTO> putUsuario(@PathVariable Long id, @RequestBody AtualizarUsuarioForm form){
        Usuario user = form.atualizar(id, usuarioRepository);
        return ResponseEntity.ok(new UsuarioCadastradoDTO(user));
    }

}
