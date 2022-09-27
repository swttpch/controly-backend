package controly.repository;

import controly.model.entity.TopicoEntity;
import controly.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<TopicoEntity,Long> {

}
