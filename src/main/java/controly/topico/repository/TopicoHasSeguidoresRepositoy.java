package controly.topico.repository;

import controly.model.entity.TopicoHasSeguidoresEntity;
import controly.modules.topico.entities.TopicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicoHasSeguidoresRepositoy extends JpaRepository<TopicoHasSeguidoresEntity, Long > {
    List<TopicoHasSeguidoresEntity> findTopicosHasSeguidoresTopicoEntityByUsuario_IdUsuario(Long idUsuario);

    @Query("SELECT COUNT(id_usuario) FROM TopicoHasSeguidoresEntity WHERE id_topico = :idTopico")
    Integer countTopicoHasSeguidoresByIdUsuario(Long idTopico);
}
