package controly.modules.perfilAndUsuario.controller;


import controly.modules.perfilAndUsuario.entities.UsuarioEntity;
import controly.modules.perfilAndUsuario.form.CadastrarNovoUsuarioForm;
import controly.modules.recuperarSenha.form.RecuperarSenhaForm;
import controly.modules.recuperarSenha.service.RecuperarSenhaService;
import controly.modules.perfilAndUsuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.util.List;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RecuperarSenhaService recuperarSenhaService;

    public UsuarioController() {
    }

    @PreAuthorize("hasAnyRole('ADM')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuario(@PathVariable Long id){
        return usuarioService.getUsuarioCadastrado(id);
    }

    @PreAuthorize("hasAnyRole('ADM')")
    @GetMapping
    public ResponseEntity<List<UsuarioEntity>> getListUsuarios(){
        return usuarioService.getListUsuarios();
    }

    @PreAuthorize("hasAnyRole('ADM')")
    @PostMapping
    public ResponseEntity<String> cadastrarUsuario(@RequestBody CadastrarNovoUsuarioForm user){
        return usuarioService.cadastrarUsuario(user);
    }

    @PreAuthorize("hasAnyRole('ADM')")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletarUsuario(@PathVariable Long id){
        return usuarioService.deletarUsuario(id);
    }

    @PreAuthorize("hasAnyRole('ADM')")
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarUsuario(@PathVariable Long id,
                                                   @RequestBody CadastrarNovoUsuarioForm form){
        return usuarioService.atualizarUsuario(id,form);
    }

    @PreAuthorize("hasAnyRole('ADM')")
    @PostMapping("/recuperar-senha")
    public ResponseEntity<?> recuperarSenha(@RequestBody RecuperarSenhaForm form){
        return recuperarSenhaService.recuperarSenha(form);
    }

}
