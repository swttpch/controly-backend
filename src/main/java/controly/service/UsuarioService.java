package controly.service;

import controly.ValidacaoUsuario;
import controly.dto.AtualizarUsuarioRequest;
import controly.dto.GitHubInformacoes;
import controly.entities.UserEntity;
import controly.dto.CadastrarNovoUsuarioForm;
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
public class UsuarioService {

    @Autowired
    private UserRepository userRepository;


    public UsuarioService() {
    }

    @Transactional
    public ResponseEntity<UserEntity> cadastrarUsuario(CadastrarNovoUsuarioForm novoUser) {

        String usuarioInvalido = new ValidacaoUsuario().validar(novoUser);

        if (usuarioInvalido == null) {

            if (userRepository.findByEmail(novoUser.getEmail()).isEmpty()) {

                UserEntity userEntity = novoUser.converter();

                userRepository.save(userEntity);
                return ResponseEntity.status(HttpStatus.CREATED).body(userEntity);

            } else {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já existe na base de dados");
            }

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, usuarioInvalido);
        }

    }

    @Transactional
    public Optional<UserEntity> buscarUsuarioPorId(Long id) {
        return userRepository.findByIdUser(id);
    }

    public ResponseEntity<?> getUsuarioCadastrado(Long id) {
        Optional<UserEntity> usuarioEntity = userRepository.findById(id);
        if (usuarioEntity.isPresent()) {
            return ResponseEntity.status(200).body(usuarioEntity);
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "O id não corresponde a nenhum usuário encontrado na base de dados"
        );
    }

    public ResponseEntity<List<UserEntity>> getListUsuarios() {
        List<UserEntity> lista = userRepository.findAll();

        return lista.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(lista);
    }

    @Transactional
    public ResponseEntity<String> deletarUsuario(Long id) {
        Optional<UserEntity> usuario = this.buscarUsuarioPorId(id);

        if (usuario.isPresent()) {
            usuario.get().setAtivo(false);
            userRepository.save(usuario.get());
            return ResponseEntity.status(200).body("Usuario desativado com sucesso");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
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
        UserEntity usuario = buscarUsuarioPorId(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        usuario.setNickname(novoApelido);
    }

    @Transactional
    public void atualizarAvatar(Long idUsuario, String novoAvatar) {
        UserEntity usuario = buscarUsuarioPorId(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
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
