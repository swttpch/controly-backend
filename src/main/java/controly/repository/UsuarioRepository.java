package controly.repository;

import controly.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Long> {

    Optional<UsuarioEntity> findByEmail(String email);
    Optional<UsuarioEntity> findByIdUsuario(Long id);
    Optional<UsuarioEntity> findByIdGithub(Long id);
    Optional<UsuarioEntity> findByEmailAndSenha(String email, String senha);


}
