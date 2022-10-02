package controly.repository;

import controly.model.entity.TopicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<TopicoEntity, Long> {

    Optional<TopicoEntity> findByIdTopico(Long id);

}
