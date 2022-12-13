package controly.repository;

import controly.entities.ComentarioEntity;
import controly.entities.PontuacaoComentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface PontuacaoComentarioRepository extends JpaRepository<PontuacaoComentario, Long> {
    Optional<List<PontuacaoComentario>> findByComentario(ComentarioEntity comentario);

    @Query(value = "SELECT m FROM PontuacaoComentario m WHERE m.comentario.idComentario = ?1 AND m.usuario.idUsuario = ?2")
    Optional<PontuacaoComentario> existByComentarioAndUsuario(Long idComentario, Long idusuario);

    @Transactional
    @Modifying
    @Query("DELETE FROM PontuacaoComentario WHERE comentario.idComentario = :idComentario and usuario.idUsuario = :idUsuario")
    void deleteByComentario_idComentario(Long idComentario, Long idUsuario);

}
