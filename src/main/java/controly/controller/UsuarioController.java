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
@RequestMapping("/usuarios")
public class UsuarioController {
    Autenticacao auth = new Autenticacao();

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/teste")
    public ResponseEntity<UsuarioCadastradoDTO> getUsuarios(){

        // Criar?

        return null;
    }

    @GetMapping
    public ResponseEntity<?> getUsuario(@RequestBody Long id){
        return usuarioService.getUsuarioCadastrado(id);
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

    /*
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UsuarioCadastradoDTO> putUsuario(@PathVariable Long id, @RequestBody AtualizarUsuarioForm user){
        return ResponseEntity.ok(new UsuarioCadastradoDTO(user));
    }
    */
}
