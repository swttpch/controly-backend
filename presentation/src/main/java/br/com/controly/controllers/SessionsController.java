package br.com.controly.controllers;


import br.com.controly.config.Constant;
import br.com.controly.dtos.DataGithubPostDTO;
import br.com.controly.dtos.GithubUserDTO;
import br.com.controly.dtos.LoginDTO;
import br.com.controly.domain.entities.Users;
import br.com.controly.services.AuthService;
import br.com.controly.services.GithubService;
import br.com.controly.usecases.LoginUsecase;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;




@RestController
@RequestMapping("/sessions")
@CrossOrigin(origins = "*")
public class SessionsController {
    private final int EXPIRATION_IN_SECONDS = 60*60*24*30;

    @Autowired
    private AuthService authService;
    @Autowired
    private GithubService githubService;

    @Autowired
    private LoginUsecase loginUsecase;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginDTO login, HttpServletResponse response){
        Users user = authService.login(login.getEmail(), login.getPassword());
        if (user==null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something goes Wrong");
        var sessionToken = loginUsecase.execute(login);
        Cookie sessionCookie = new Cookie("session_id", sessionToken);
        sessionCookie.setPath("/");
        sessionCookie.setMaxAge(EXPIRATION_IN_SECONDS);
        sessionCookie.setSecure(false);
        sessionCookie.setHttpOnly(true);
        response.addCookie(sessionCookie);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("")
    public ResponseEntity<Users> getUser(@RequestParam String token){
        Users user = authService.getUser(token);
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
    public ResponseEntity<Users> postGitHubUser(@RequestBody GithubUserDTO githubUser){
        Users user =  authService.githubAuth(githubUser);
        if (user == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something goes Wrong");
        return ResponseEntity.status(200).body(user);
    }
}
