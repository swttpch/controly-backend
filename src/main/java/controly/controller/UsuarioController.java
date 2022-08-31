package controly.controller;


import controly.controller.dto.UsuarioCadastradoDTO;
import controly.controller.form.AtualizarUsuarioForm;
import controly.controller.form.CadastrarNovoUsuarioForm;
import controly.repository.UsuarioRepository;
import controly.model.Usuario;
import controly.security.Autenticacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    Autenticacao auth = new Autenticacao();

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<UsuarioCadastradoDTO> getUsuarios(){

        // Criar?

        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioCadastradoDTO> getUsuario(@PathVariable Long id){
        Optional<Usuario> user = usuarioRepository.findById(id);
        if(user.isPresent()){
            return ResponseEntity.ok(new UsuarioCadastradoDTO(user.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<UsuarioCadastradoDTO> cadastrarUsuario(@RequestBody CadastrarNovoUsuarioForm user,
                                                                 UriComponentsBuilder uriBuilder){
        Usuario usuario = user.converter();
        usuarioRepository.save(usuario);
        URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioCadastradoDTO(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletarUsuario(@PathVariable Long id){
        usuarioRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UsuarioCadastradoDTO> putUsuario(@PathVariable Long id, @RequestBody AtualizarUsuarioForm form){
        Usuario user = form.atualizar(id, usuarioRepository);
        return ResponseEntity.ok(new UsuarioCadastradoDTO(user));
    }

}
