package controly.repository;

import controly.model.entity.TopicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<TopicoEntity, Long> {
    Optional<TopicoEntity> findByNome(String name);

    Optional<TopicoEntity> findById(Long id);
}
