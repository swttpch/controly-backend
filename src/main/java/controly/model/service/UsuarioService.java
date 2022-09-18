package controly.model.service;

import controly.controller.form.CadastrarNovoUsuarioForm;
import controly.model.ValidacaoUsuario;
import controly.model.entity.Usuario;
import controly.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Optional;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public ResponseEntity<String> cadastrarUsuario(CadastrarNovoUsuarioForm novoUser){

        String usuarioInvalido = new ValidacaoUsuario().validar(novoUser);

        if(usuarioInvalido==null){

            if (usuarioRepository.findByEmail(novoUser.getEmail()).isEmpty()) {

                Usuario usuario = novoUser.converter();
                usuarioRepository.save(usuario);
                return ResponseEntity.status(HttpStatus.CREATED).body("Usuario cadastrado com sucesso");

            }else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já existe na base de dados");
            }

        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(usuarioInvalido);
        }

    }

    public Optional<Usuario> buscarUsuarioPorId(Long id){
        return usuarioRepository.findById(id);
    }

    public ResponseEntity<?> getUsuarioCadastrado(Long id){
        return null;
    }


    @Transactional
    public ResponseEntity<String> deletarUsuario(Long id){
        Optional<Usuario> usuario = this.buscarUsuarioPorId(id);

        if(usuario.isPresent()){
            usuarioRepository.deleteById(id);
            return ResponseEntity.status(200).body("Usuario deletado com sucesso");
        } else {
            return ResponseEntity.status(404).body("Usuário não encontrado");
        }
    }

    public ResponseEntity<String> atualizarUsuario(Long id, CadastrarNovoUsuarioForm form) {

        String usuarioInvalido = new ValidacaoUsuario().validar(form);

        if(usuarioInvalido==null) {

            Optional<Usuario> usuario = buscarUsuarioPorId(id);

            if (usuario.isPresent()) {

                Usuario usuarioCadastrado = usuario.get();

                if (!usuarioCadastrado.getNome().equals(form.getNome())) {
                    usuarioCadastrado.setNome(form.getNome());
                }
                if (!usuarioCadastrado.getEmail().equals(form.getEmail())) {
                    usuarioCadastrado.setEmail(form.getEmail());
                }
                if (!usuarioCadastrado.getSenha().equals(form.getSenha())) {
                    usuarioCadastrado.setSenha(form.getSenha());
                }


                usuarioRepository.save(usuarioCadastrado);
                return ResponseEntity.status(201).body("Usuario atualizado com sucesso");

            } else {
                return ResponseEntity.status(404).body("Usuario não encontrado");
            }
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(usuarioInvalido);
        }
    }

}
