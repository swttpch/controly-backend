package controly.service;

import controly.dto.AtualizarUsuarioRequest;
import controly.dto.GitHubInformacoes;
import controly.entities.UserEntity;
import controly.dto.CreateNewUserRequest;
import controly.exception.EmailAlreadyExistsException;
import controly.exception.UsersIdNotFould;
import controly.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Transactional
    public UserEntity createNewUser(CreateNewUserRequest newUser) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(newUser.getEmail());
        if (optionalUser.isPresent()) throw new EmailAlreadyExistsException();
        return userRepository.save(newUser.convert());
    }

    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(UsersIdNotFould::new);
    }

    public List<UserEntity> getListOfUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public int deleteUserById(Long id) {
        UserEntity user = this.getUserById(id);
        // if user is already disabled then return 0
        // otherwise, disable this user and return 1
        return user.disableUser();
    }

    @Transactional
    public ResponseEntity<String> atualizarUsuario(Long id, AtualizarUsuarioRequest form) {
        UserEntity usuario = userRepository.findByIdUser(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        usuario.setAbout("");

        if (!usuario.getName().equals(form.getNome()) && !form.getNome().isBlank())
            usuario.setName(form.getNome());

        if (!usuario.getNickname().equals(form.getApelido()) && !form.getApelido().isBlank())
            usuario.setNickname(form.getApelido());

        if (!usuario.getAbout().equals(form.getDescricao()) && !form.getDescricao().isBlank())
            usuario.setAbout(form.getDescricao());

        return ResponseEntity.status(200).build();
    }

    @Transactional
    public void atualizarApelido(Long idUsuario, String novoApelido) {
        UserEntity usuario = getUserById(idUsuario);
        usuario.setNickname(novoApelido);
    }

    @Transactional
    public void atualizarAvatar(Long idUsuario, String novoAvatar) {
        UserEntity usuario = getUserById(idUsuario);
        usuario.setAvatar(novoAvatar);
    }

    public UserEntity login(String email, String senha) {
        Optional<UserEntity> usuarioPromisse = userRepository.findByEmailAndPassword(email, senha);
        return usuarioPromisse.orElse(null);
    }


    public ResponseEntity<UserEntity> autenticarGithub(GitHubInformacoes usuario) {
        Optional<UserEntity> optionalUsuarioEntity = userRepository.findByIdGithub(usuario.getIdGithub());
        if (optionalUsuarioEntity.isPresent()){
            return ResponseEntity.status(200).body(optionalUsuarioEntity.get());
        }
        UserEntity novoUsuario = usuario.converter();
        userRepository.save(novoUsuario);
        return ResponseEntity.status(201).body(novoUsuario);
    }

    public ResponseEntity<Void> verificaEmailExiste(String email) {
        Optional<UserEntity> usuarioEntity = userRepository.findByEmail(email);
        if (usuarioEntity.isPresent()) {
            return ResponseEntity.status(200).build();
        }
        return  ResponseEntity.status(404).build();
    }
}
