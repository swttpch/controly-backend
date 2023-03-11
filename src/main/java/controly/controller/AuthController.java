package controly.controller;


import controly.config.Constant;
import controly.dto.DataGithubPostRequest;
import controly.dto.GithubUserRequest;
import controly.dto.LoginRequest;
import controly.entity.UserEntity;
import controly.service.AuthService;
import controly.service.GithubService;
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
    public ResponseEntity<String> login(@RequestBody LoginRequest login){
        UserEntity user = authService.login(login.getEmail(), login.getPassword());
        if (user==null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something goes Wrong");
        return ResponseEntity.status(200).body(user.getToken());
    }

    @PostMapping("/login")
    public ResponseEntity<UserEntity> login2(@RequestBody LoginRequest login){
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
        DataGithubPostRequest data = new DataGithubPostRequest(code);
        String response = githubService.consumeApi(Constant.GITHUB_AUTH_ACCESSTOKEN_URL, data);
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping("/github")
    public ResponseEntity<UserEntity> postGitHubUser(@RequestBody GithubUserRequest githubUser){
        UserEntity user =  authService.githubAuth(githubUser);
        if (user == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something goes Wrong");
        return ResponseEntity.status(200).body(user);
    }
}
