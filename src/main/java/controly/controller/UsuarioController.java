package controly.controller;


import controly.config.Constant;
import controly.dto.AtualizarUsuarioRequest;
import controly.dto.DataGithubPostRequest;
import controly.dto.GitHubInformacoes;
import controly.entities.UserEntity;
import controly.dto.CadastrarNovoUsuarioForm;
import controly.service.GithubService;
import controly.service.UsuarioService;
import controly.dto.RecuperarSenhaForm;
import controly.service.RecuperarSenhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
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
    public ResponseEntity<List<UserEntity>> getListUsuarios() {
        return usuarioService.getListUsuarios();
    }


    @PostMapping
    public ResponseEntity<UserEntity> cadastrarUsuario(@RequestBody CadastrarNovoUsuarioForm user) {
        return usuarioService.cadastrarUsuario(user);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletarUsuario(@PathVariable Long id) {
        return usuarioService.deletarUsuario(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarUsuario(@PathVariable Long id,
                                                   @RequestBody AtualizarUsuarioRequest form) {
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

    @PostMapping("/github")
    public ResponseEntity<UserEntity> postGitHubUser(@RequestBody GitHubInformacoes usuario){
        return usuarioService.autenticarGithub(usuario);
    }


        @PutMapping("/atualizar/apelido-avatar/{idUsuario}")
    public ResponseEntity<?> atualizarDadosUsuario(
            @PathVariable Long idUsuario,
            @RequestParam(required = false) String apelido,
            @RequestParam(required = false) String avatar
    ) {
        if(apelido == null && avatar == null) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passe um avatar ou um apelido para ser atualizado");
        }
        if (apelido != null) {
            usuarioService.atualizarApelido(idUsuario, apelido);
        }
        if (avatar != null) {
            usuarioService.atualizarAvatar(idUsuario, avatar);
        }
        return getUsuario(idUsuario);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Void> verificaEmailExiste(@PathVariable String email){
        return usuarioService.verificaEmailExiste(email);
    }
}
