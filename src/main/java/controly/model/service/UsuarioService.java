package controly.model.service;

import controly.controller.form.CadastrarNovoUsuarioForm;
import controly.model.ValidacaoUsuario;
import controly.model.entity.UsuarioEntity;
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

                UsuarioEntity usuarioEntity = novoUser.converter();
                usuarioRepository.save(usuarioEntity);
                return ResponseEntity.status(HttpStatus.CREATED).body("UsuarioEntity cadastrado com sucesso");

            }else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já existe na base de dados");
            }

        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(usuarioInvalido);
        }

    }

    public Optional<UsuarioEntity> buscarUsuarioPorId(Long id){
        return usuarioRepository.findById(id);
    }

    public ResponseEntity<?> getUsuarioCadastrado(Long id){
        return null;
    }


    @Transactional
    public ResponseEntity<String> deletarUsuario(Long id){
        Optional<UsuarioEntity> usuario = this.buscarUsuarioPorId(id);

        if(usuario.isPresent()){
            usuarioRepository.deleteById(id);
            return ResponseEntity.status(200).body("UsuarioEntity deletado com sucesso");
        } else {
            return ResponseEntity.status(404).body("Usuário não encontrado");
        }
    }

    public ResponseEntity<String> atualizarUsuario(Long id, CadastrarNovoUsuarioForm form) {

        String usuarioInvalido = new ValidacaoUsuario().validar(form);

        if(usuarioInvalido==null) {

            Optional<UsuarioEntity> usuario = buscarUsuarioPorId(id);

            if (usuario.isPresent()) {

                UsuarioEntity usuarioEntityCadastrado = usuario.get();

                if (!usuarioEntityCadastrado.getNome().equals(form.getNome())) {
                    usuarioEntityCadastrado.setNome(form.getNome());
                }
                if (!usuarioEntityCadastrado.getEmail().equals(form.getEmail())) {
                    usuarioEntityCadastrado.setEmail(form.getEmail());
                }
                if (!usuarioEntityCadastrado.getSenha().equals(form.getSenha())) {
                    usuarioEntityCadastrado.setSenha(form.getSenha());
                }


                usuarioRepository.save(usuarioEntityCadastrado);
                return ResponseEntity.status(201).body("UsuarioEntity atualizado com sucesso");

            } else {
                return ResponseEntity.status(404).body("UsuarioEntity não encontrado");
            }
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(usuarioInvalido);
        }
    }

}
