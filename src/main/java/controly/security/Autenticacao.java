package controly.security;


import controly.controller.dto.UsuarioCadastradoDTO;
import controly.controller.form.CadastrarNovoUsuarioForm;
import controly.model.entity.UsuarioEntity;
import controly.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class Autenticacao {

    static private List<UsuarioEntity> usuarioEntities;

    public Autenticacao() {
        this.usuarioEntities = new ArrayList<>();
    }

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Transactional
    public ResponseEntity<UsuarioCadastradoDTO> postUsuario(CadastrarNovoUsuarioForm user) {
        //Long id = usuarioEntities.isEmpty() ? 1 : usuarioEntities.get(usuarioEntities.size() - 1).getIdUsuario() + 1;
        //usuarioEntity.setIdUsuario(id);
        //this.usuarioEntities.add(usuarioEntity);

        UsuarioEntity usuarioEntity = user.converter();

        usuarioRepository.save(usuarioEntity);

        return null;
    }

    public UsuarioEntity deleteUsuario(Long id){
        UsuarioEntity usuarioEntity = usuarioEntities.stream()
                .filter(us -> id == us.getIdUsuario())
                .findFirst()
                .orElse(null);
        usuarioEntities.remove(usuarioEntity);
        return usuarioEntity;
    }

    public UsuarioEntity putUsuario(Long id, UsuarioEntity usuarioEntity){
        for (int i = 0; i < usuarioEntities.size(); i++) {
            if (usuarioEntities.get(i).getIdUsuario() == id) {
                usuarioEntity.setIdUsuario(usuarioEntities.get(i).getIdUsuario());
                usuarioEntities.set(i, usuarioEntity);
                return usuarioEntity;
            };
        }
        return null;

    }

    public UsuarioEntity getUsuario(Long id) {
        return usuarioEntities.stream()
                .filter(us -> id == us.getIdUsuario())
                .findFirst()
                .orElse(null);
    }

    public UsuarioEntity autenticarLogin(String email, String senha){
        System.out.println(usuarioEntities);
        for (UsuarioEntity usuarioEntity : usuarioEntities) {
            if (email.equals(usuarioEntity.getEmail()) && senha.equals(usuarioEntity.getSenha()))
                return usuarioEntity;
        }
        return null;
    }

    public List<UsuarioEntity> getUsuarios(){
        return usuarioEntities;
    }
}
