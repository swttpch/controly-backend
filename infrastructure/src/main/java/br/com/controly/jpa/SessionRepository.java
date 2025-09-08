package br.com.controly.jpa;

import br.com.controly.domain.entities.Sessions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Sessions, Long> {
    Optional<Sessions> findByUserId(Long id);
}
