package br.com.controly.controllers;


import br.com.controly.config.Constant;
import br.com.controly.config.LoggerConfig;
import br.com.controly.dtos.DataGithubPostDTO;
import br.com.controly.dtos.GithubUserDTO;
import br.com.controly.dtos.LoginDTO;
import br.com.controly.domain.entities.UserEntity;
import br.com.controly.services.AuthService;
import br.com.controly.services.GithubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;




@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {


    @Autowired
    private AuthService authService;
    @Autowired
    private GithubService githubService;

    @PostMapping()
    public ResponseEntity<String> login(@RequestBody LoginDTO login){
        LoggerConfig.getLogger().info("Teste de Log!!!");
        UserEntity user = authService.login(login.getEmail(), login.getPassword());
        if (user==null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something goes Wrong");
        return ResponseEntity.status(200).body(user.getToken());
    }

    @PostMapping("/login")
    public ResponseEntity<UserEntity> login2(@RequestBody LoginDTO login){
        UserEntity user = authService.login(login.getEmail(), login.getPassword());
        if (user==null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something goes Wrong");
        return ResponseEntity.status(200).body(user);
    }

    @GetMapping("")
    public ResponseEntity<UserEntity> getUser(@RequestParam String token){
        UserEntity user = authService.getUser(token);
        if (user==null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something goes Wrong");
        return ResponseEntity.status(200).body(user);
    }

    @GetMapping("/github")
    public ResponseEntity<String> getGithubUser(@RequestParam String code) {
        DataGithubPostDTO data = new DataGithubPostDTO(code);
        String response = githubService.consumeApi(Constant.GITHUB_AUTH_ACCESSTOKEN_URL, data);
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping("/github")
    public ResponseEntity<UserEntity> postGitHubUser(@RequestBody GithubUserDTO githubUser){
        UserEntity user =  authService.githubAuth(githubUser);
        if (user == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something goes Wrong");
        return ResponseEntity.status(200).body(user);
    }
}
