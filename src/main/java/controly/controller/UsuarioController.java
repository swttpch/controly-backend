package controly.controller;

import controly.model.Autenticacao;
import controly.model.Usuario;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    Autenticacao auth = new Autenticacao();

    @GetMapping("/")
    public List<Usuario> getUsuarios(){
        return auth.getUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario getUsuario(@PathVariable int id){
        return auth.getUsuario(id);
    }

    @PostMapping()
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario){
        return auth.postUsuario(usuario);
    }

    @DeleteMapping("/{id}")
    public Usuario deletarUsuario(@PathVariable int id){
        return auth.deleteUsuario(id);
    }

    @PutMapping("/{id}")
    public Usuario putUsuario(@PathVariable int id, @RequestBody Usuario usuario){
        return auth.putUsuario(id, usuario);
    }

}
