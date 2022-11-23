package controly.modules.perfilAndUsuario.controller;


import controly.config.Constant;
import controly.modules.perfilAndUsuario.dto.DataGithubPostRequest;
import controly.modules.perfilAndUsuario.entities.UsuarioEntity;
import controly.modules.perfilAndUsuario.form.CadastrarNovoUsuarioForm;
import controly.modules.perfilAndUsuario.service.GithubService;
import controly.modules.recuperarSenha.form.RecuperarSenhaForm;
import controly.modules.recuperarSenha.service.RecuperarSenhaService;
import controly.modules.perfilAndUsuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RecuperarSenhaService recuperarSenhaService;

    @Autowired
    private GithubService githubService;

    public UsuarioController() {
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuario(@PathVariable Long id) {
        return usuarioService.getUsuarioCadastrado(id);
    }


    @GetMapping
    public ResponseEntity<List<UsuarioEntity>> getListUsuarios() {
        return usuarioService.getListUsuarios();
    }


    @PostMapping
    public ResponseEntity<String> cadastrarUsuario(@RequestBody CadastrarNovoUsuarioForm user) {
        return usuarioService.cadastrarUsuario(user);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletarUsuario(@PathVariable Long id) {
        return usuarioService.deletarUsuario(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarUsuario(@PathVariable Long id,
                                                   @RequestBody CadastrarNovoUsuarioForm form) {
        return usuarioService.atualizarUsuario(id, form);
    }


    @PostMapping("/recuperar-senha")
    public ResponseEntity<?> recuperarSenha(@RequestBody RecuperarSenhaForm form) {
        return recuperarSenhaService.recuperarSenha(form);
    }


    @GetMapping("/github")
    public ResponseEntity<String> getGithubUser(@RequestParam String code) {
        System.out.println(code);
        DataGithubPostRequest data = new DataGithubPostRequest(code);
        String response = githubService.consumeApi(Constant.GITHUB_AUTH_ACCESSTOKEN_URL, data);
        return ResponseEntity.status(200).body(response);
    }


    @PutMapping("atualizar/apelido-avatar/{idUsuario}")
    public ResponseEntity<String> atualizarDadosUsuario(
            @PathVariable Long idUsuario,
            @RequestParam(required = false) String apelido,
            @RequestParam(required = false) Integer avatar
    ) {

        if (apelido != null) {
            return usuarioService.atualizarApelido(idUsuario, apelido);

        } else if (avatar != null) {
            return usuarioService.atualizarAvatar(idUsuario, avatar);
        } else {
            return ResponseEntity.status(200).body("Passe ou um apelido, ou um avatar");

        }
    }



}
