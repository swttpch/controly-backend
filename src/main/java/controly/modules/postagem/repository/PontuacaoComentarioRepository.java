package controly.modules.postagem.repository;

import controly.modules.comentario.entities.ComentarioEntity;
import controly.modules.pontuacao.entities.pontuacaoComentario.PontuacaoComentario;
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

    @Transactional @Modifying
    @Query(value="UPDATE PontuacaoComentario p SET p.pontuacao = ?3 WHERE p.comentario.idComentario = ?1 AND p.usuario.idUsuario = ?2")
    void setPontuacaoFor(Long idcomentario, Long idusuario, int pontuacao);
}
