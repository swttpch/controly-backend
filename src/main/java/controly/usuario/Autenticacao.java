package controly.usuario;


import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Autenticacao {
    static private List<Usuario> usuarios;

    public Autenticacao() {
        this.usuarios = new ArrayList<>();
    }

    public Usuario postUsuario(Usuario usuario) {
        int id = usuarios.isEmpty() ? 1 : usuarios.get(usuarios.size() - 1).getId() + 1;
        usuario.setId(id);
        this.usuarios.add(usuario);
        return usuario;
    }

    public Usuario deleteUsuario(int id){
        Usuario usuario = usuarios.stream()
                .filter(us -> id == us.getId())
                .findFirst()
                .orElse(null);
        usuarios.remove(usuario);
        return usuario;
    }

    public Usuario putUsuario(int id, Usuario usuario){
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId() == id) {
                usuario.setId(usuarios.get(i).getId());
                usuarios.set(i, usuario);
                return usuario;
            };
        }
        return null;

    }

    public Usuario getUsuario(int id) {
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
