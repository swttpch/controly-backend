package controly.repository;

import controly.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByIdUser(Long id);
    Optional<UserEntity> findByIdGithub(Long id);
    Optional<UserEntity> findByEmailAndPassword(String email, String senha);


}
