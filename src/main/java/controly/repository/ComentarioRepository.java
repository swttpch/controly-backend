package controly.repository;

import controly.model.entity.ComentarioEntity;
import controly.model.entity.PontuacaoPostagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ComentarioRepository extends JpaRepository<ComentarioEntity, Long> {

}
