package controly.modules.perfilAndUsuario.service;

import controly.modules.perfilAndUsuario.ValidacaoUsuario;
import controly.modules.perfilAndUsuario.entities.UsuarioEntity;
import controly.modules.perfilAndUsuario.form.CadastrarNovoUsuarioForm;
import controly.modules.perfilAndUsuario.repository.UsuarioRepository;
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

    public ResponseEntity<String> atualizarUsuario(Long id, CadastrarNovoUsuarioForm form) {

        String usuarioInvalido = new ValidacaoUsuario().validar(form);

        if (usuarioInvalido == null) {

            Optional<UsuarioEntity> usuario = buscarUsuarioPorId(id);

            if (usuario.isPresent()) {

                UsuarioEntity usuarioEntityCadastrado = usuario.get();

                if (!usuarioEntityCadastrado.getNome().equals(form.getNome())) {
                    usuarioEntityCadastrado.setNome(form.getNome());
                }
                if (!usuarioEntityCadastrado.getApelido().equals(form.getApelido())) {
                    usuarioEntityCadastrado.setApelido(form.getApelido());
                }
                if (!usuarioEntityCadastrado.getEmail().equals(form.getEmail())) {
                    usuarioEntityCadastrado.setEmail(form.getEmail());
                }
                if (!usuarioEntityCadastrado.getSenha().equals(form.getSenha())) {
                    usuarioEntityCadastrado.setSenha(form.getSenha());
                }


                usuarioRepository.save(usuarioEntityCadastrado);
                return ResponseEntity.status(201).body("Usuario atualizado com sucesso");

            } else {
                return ResponseEntity.status(404).body("UsuarioEntity não encontrado");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
    }

    @Transactional
    public void atualizarApelido(Long idUsuario, String novoApelido) {
        UsuarioEntity usuario = buscarUsuarioPorId(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        usuario.setApelido(novoApelido);
    }

    @Transactional
    public void atualizarAvatar(Long idUsuario, Integer novoAvatar) {
        UsuarioEntity usuario = buscarUsuarioPorId(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        usuario.setAvatar(novoAvatar);
    }

    public UsuarioEntity login(String email, String senha) {
        Optional<UsuarioEntity> usuarioPromisse = usuarioRepository.findByEmailAndSenha(email, senha);
        return usuarioPromisse.orElse(null);
    }


}
