package br.com.controly.jpa;

import br.com.controly.domain.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {

    Optional<Users> findByEmail(String email);
    Optional<Users> findByIdGithub(Long id);

    Optional<Users> findByToken(String token);
}
