package controly.controller;


import controly.config.Constant;
import controly.dto.UpdateUsersInfoRequest;
import controly.dto.DataGithubPostRequest;
import controly.dto.GithubUserRequest;
import controly.entities.UserEntity;
import controly.dto.CreateNewUserRequest;
import controly.service.GithubService;
import controly.service.UserService;
import controly.dto.RecuperarSenhaForm;
import controly.service.PasswordRecoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuarios")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordRecoveryService passwordRecoveryService;
    @Autowired
    private GithubService githubService;

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(userService.getUserById(id));
    }
    @GetMapping
    public ResponseEntity<List<UserEntity>> getListOfUsers() {
        List<UserEntity> users =  userService.getListOfUsers();
        return users.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(users);
    }

    @PostMapping
    public ResponseEntity<UserEntity> createNewUser(@RequestBody @Valid CreateNewUserRequest user) {
        UserEntity newUser = userService.createNewUser(user);
        return ResponseEntity.status(201).body(newUser);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        int usersDeleted = userService.deleteUserById(id);
        if (usersDeleted == 0)
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User is already disabled.");
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUsersInfo(@PathVariable Long id,
                                                  @RequestBody @Valid UpdateUsersInfoRequest form) {
        if(userService.updateUsersInfo(id, form)!= 1)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something goes wrong.");
        return ResponseEntity.status(200).body("User's data updated.");
    }


    @PostMapping("/recuperar-senha")
    public ResponseEntity<?> passwordRecovery(@RequestBody RecuperarSenhaForm form) {
        passwordRecoveryService.passwordRecovery(form);
        return ResponseEntity.status(200).body("New password sended to users e-mail.");
    }


    @GetMapping("/github")
    public ResponseEntity<String> getGithubUser(@RequestParam String code) {
        DataGithubPostRequest data = new DataGithubPostRequest(code);
        String response = githubService.consumeApi(Constant.GITHUB_AUTH_ACCESSTOKEN_URL, data);
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping("/github")
    public ResponseEntity<UserEntity> postGitHubUser(@RequestBody GithubUserRequest githubUser){
        UserEntity user =  userService.githubAuth(githubUser);
        if (user == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something goes Wrong");
        return ResponseEntity.status(200).body(user);
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
            userService.atualizarApelido(idUsuario, apelido);
        }
        if (avatar != null) {
            userService.atualizarAvatar(idUsuario, avatar);
        }
        return getUserById(idUsuario);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Void> verificaEmailExiste(@PathVariable String email){
        return userService.verificaEmailExiste(email);
    }
}
