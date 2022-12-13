package controly.repository;

import controly.entities.ComentarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<ComentarioEntity, Long> {

    ComentarioEntity findByIdComentario(Long id);

    List<ComentarioEntity> findByPostagemIdPostagem(Long idPostagem);
}
