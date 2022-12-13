package controly.repository;

import controly.entities.TopicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<TopicoEntity,Long> {
    TopicoEntity findByIdTopico(Long id);
}