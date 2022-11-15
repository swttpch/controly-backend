package controly.config.security;

import controly.modules.perfilAndUsuario.entities.UsuarioEntity;
import controly.modules.perfilAndUsuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImplements implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsuarioEntity usuarioAcionadorEntity = usuarioRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("Email not found: "+email));
        return new User(
                usuarioAcionadorEntity.getUsername(),
                usuarioAcionadorEntity.getPassword(),
                true,
                true,
                true,
                true,
                usuarioAcionadorEntity.getAuthorities()
        );
    }
}
