package controly.repository;

import controly.model.entity.TopicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicoRepository extends JpaRepository<TopicoEntity,Long> {
    TopicoEntity findByIdTopico(Long id);

//    @Query(nativeQuery = true, value = "select t.* from tb_topico_has_seguidores tp " +
//            "join tb_topico t " +
//            "on t.id_topico = tp.id_topico where id_usuario = :idUsuario")
    List<TopicoEntity> findTopicoByIdUsuario(Long idUsuario);
}
