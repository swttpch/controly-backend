package controly.topico.repository;

import controly.model.entity.TopicoHasSeguidoresEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicoHasSeguidoresRepositoy extends JpaRepository<TopicoHasSeguidoresEntity, Long > {
    List<TopicoHasSeguidoresEntity> findTopicosHasSeguidoresTopicoEntityByUsuario_IdUsuario(Long idUsuario);
}
