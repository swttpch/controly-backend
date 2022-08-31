package controly.security;


import controly.controller.dto.UsuarioCadastradoDTO;
import controly.controller.form.CadastrarNovoUsuarioForm;
import controly.repository.UsuarioRepository;
import controly.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class Autenticacao {

    static private List<Usuario> usuarios;

    public Autenticacao() {
        this.usuarios = new ArrayList<>();
    }

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Transactional
    public ResponseEntity<UsuarioCadastradoDTO> postUsuario(CadastrarNovoUsuarioForm user) {
        //Long id = usuarios.isEmpty() ? 1 : usuarios.get(usuarios.size() - 1).getId() + 1;
        //usuario.setId(id);
        //this.usuarios.add(usuario);

        Usuario usuario = user.converter();

        usuarioRepository.save(usuario);

        return null;
    }

    public Usuario deleteUsuario(Long id){
        Usuario usuario = usuarios.stream()
                .filter(us -> id == us.getId())
                .findFirst()
                .orElse(null);
        usuarios.remove(usuario);
        return usuario;
    }

    public Usuario putUsuario(Long id, Usuario usuario){
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId() == id) {
                usuario.setId(usuarios.get(i).getId());
                usuarios.set(i, usuario);
                return usuario;
            };
        }
        return null;

    }

    public Usuario getUsuario(Long id) {
        return usuarios.stream()
                .filter(us -> id == us.getId())
                .findFirst()
                .orElse(null);
    }

    public Usuario autenticarLogin(String email, String senha){
        System.out.println(usuarios);
        for (Usuario usuario : usuarios) {
            if (email.equals(usuario.getEmail()) && senha.equals(usuario.getSenha()))
                return usuario;
        }
        return null;
    }

    public List<Usuario> getUsuarios(){
        return usuarios;
    }
}
