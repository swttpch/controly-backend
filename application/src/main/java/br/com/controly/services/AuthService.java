package br.com.controly.services;
import br.com.controly.dtos.GithubUserRequest;
import br.com.controly.exception.UsersEmailNotFould;
import br.com.controly.jpa.UserRepository;
import br.com.controly.domain.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;

    public UserEntity login(String email, String password) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(UsersEmailNotFould::new);
        if (!user.getPassword().equals(password))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Wrong password.");
        return user;
    }

    public UserEntity githubAuth(GithubUserRequest githubUser) {
        Optional<UserEntity> userEntityOptional = userRepository.findByIdGithub(githubUser.getIdGithub());
        if (userEntityOptional.isPresent()){
            return userEntityOptional.get();
        }
        UserEntity newUser = githubUser.convert();
        userRepository.save(newUser);
        return newUser;
    }

    public UserEntity getUser(String token){
        return userRepository.findByToken(token)
                .orElseThrow(UsersEmailNotFould::new);
    }
}
