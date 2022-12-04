package controly.modules.topico.repository;

import controly.modules.topico.entities.TopicoHasSeguidoresEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicoHasSeguidoresRepositoy extends JpaRepository<TopicoHasSeguidoresEntity, Long > {
    List<TopicoHasSeguidoresEntity> findTopicosHasSeguidoresTopicoEntityByUsuario_IdUsuario(Long idUsuario);

    @Query("SELECT COUNT(id_usuario) FROM TopicoHasSeguidoresEntity WHERE id_topico = :idTopico")
    Integer countTopicoHasSeguidoresByIdUsuario(Long idTopico);

    @Query(value = "SELECT s FROM TopicoHasSeguidoresEntity s WHERE s.topico.idTopico = :idTopico AND s.usuario.idUsuario = :idUsuario")
    TopicoHasSeguidoresEntity findTopicoHasSeguidoresEntityByTopico_idTopicoAndUsuario_idUsuario(Long idTopico, Long idUsuario);

    @Query(value = "SELECT s FROM TopicoHasSeguidoresEntity s WHERE s.topico.idTopico = :idTopico AND s.usuario.idUsuario = :idUsuario")
    Optional<TopicoHasSeguidoresEntity> existsFollowUser(Long idTopico, Long idUsuario);
}
