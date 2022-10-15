package controly.repository;

import controly.model.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Long> {

    Optional<UsuarioEntity> findByEmail(String email);
    UsuarioEntity findByIdUsuario(Long id);

    Optional<UsuarioEntity> findByEmailAndSenha(String email, String senha);
}
