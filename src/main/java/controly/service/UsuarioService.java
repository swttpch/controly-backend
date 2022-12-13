package controly.service;

import controly.ValidacaoUsuario;
import controly.dto.AtualizarUsuarioRequest;
import controly.dto.GitHubInformacoes;
import controly.entities.UsuarioEntity;
import controly.dto.CadastrarNovoUsuarioForm;
import controly.repository.UsuarioRepository;
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
    private UsuarioRepository usuarioRepository;


    public UsuarioService() {
    }

    @Transactional
    public ResponseEntity<UsuarioEntity> cadastrarUsuario(CadastrarNovoUsuarioForm novoUser) {

        String usuarioInvalido = new ValidacaoUsuario().validar(novoUser);

        if (usuarioInvalido == null) {

            if (usuarioRepository.findByEmail(novoUser.getEmail()).isEmpty()) {

                UsuarioEntity usuarioEntity = novoUser.converter();

                usuarioRepository.save(usuarioEntity);
                return ResponseEntity.status(HttpStatus.CREATED).body(usuarioEntity);

            } else {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já existe na base de dados");
            }

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, usuarioInvalido);
        }

    }

    @Transactional
    public Optional<UsuarioEntity> buscarUsuarioPorId(Long id) {
        return usuarioRepository.findByIdUsuario(id);
    }

    public ResponseEntity<?> getUsuarioCadastrado(Long id) {
        Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findById(id);
        if (usuarioEntity.isPresent()) {
            return ResponseEntity.status(200).body(usuarioEntity);
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "O id não corresponde a nenhum usuário encontrado na base de dados"
        );
    }

    public ResponseEntity<List<UsuarioEntity>> getListUsuarios() {
        List<UsuarioEntity> lista = usuarioRepository.findAll();

        return lista.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(lista);
    }

    @Transactional
    public ResponseEntity<String> deletarUsuario(Long id) {
        Optional<UsuarioEntity> usuario = this.buscarUsuarioPorId(id);

        if (usuario.isPresent()) {
            usuario.get().setAtivo(false);
            usuarioRepository.save(usuario.get());
            return ResponseEntity.status(200).body("Usuario desativado com sucesso");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
    }

    @Transactional
    public ResponseEntity<String> atualizarUsuario(Long id, AtualizarUsuarioRequest form) {
        UsuarioEntity usuario = usuarioRepository.findByIdUsuario(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        usuario.setDescricao("");

        if (!usuario.getNome().equals(form.getNome()) && !form.getNome().isBlank())
            usuario.setNome(form.getNome());

        if (!usuario.getApelido().equals(form.getApelido()) && !form.getApelido().isBlank())
            usuario.setApelido(form.getApelido());

        if (!usuario.getDescricao().equals(form.getDescricao()) && !form.getDescricao().isBlank())
            usuario.setDescricao(form.getDescricao());

        return ResponseEntity.status(200).build();
    }

    @Transactional
    public void atualizarApelido(Long idUsuario, String novoApelido) {
        UsuarioEntity usuario = buscarUsuarioPorId(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        usuario.setApelido(novoApelido);
    }

    @Transactional
    public void atualizarAvatar(Long idUsuario, String novoAvatar) {
        UsuarioEntity usuario = buscarUsuarioPorId(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        usuario.setAvatar(novoAvatar);
    }

    public UsuarioEntity login(String email, String senha) {
        Optional<UsuarioEntity> usuarioPromisse = usuarioRepository.findByEmailAndSenha(email, senha);
        return usuarioPromisse.orElse(null);
    }


    public ResponseEntity<UsuarioEntity> autenticarGithub(GitHubInformacoes usuario) {
        Optional<UsuarioEntity> optionalUsuarioEntity = usuarioRepository.findByIdGithub(usuario.getIdGithub());
        if (optionalUsuarioEntity.isPresent()){
            return ResponseEntity.status(200).body(optionalUsuarioEntity.get());
        }
        UsuarioEntity novoUsuario = usuario.converter();
        usuarioRepository.save(novoUsuario);
        return ResponseEntity.status(201).body(novoUsuario);
    }

    public ResponseEntity<Void> verificaEmailExiste(String email) {
        Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findByEmail(email);
        if (usuarioEntity.isPresent()) {
            return ResponseEntity.status(200).build();
        }
        return  ResponseEntity.status(404).build();
    }
}
