package controly.repository;

import controly.model.entity.ComentarioEntity;
import controly.model.entity.PontuacaoComentario;
import controly.model.entity.PontuacaoPostagem;
import controly.model.entity.PostagemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PontuacaoComentarioRepository extends JpaRepository<PontuacaoComentario, Long> {
    Optional<List<PontuacaoComentario>> findByComentario(ComentarioEntity comentario);

    @Query(value = "select * from tb_pontuacao_comentario where id_comentario = ?1 AND id_usuario = ?2", nativeQuery = true)
    Optional<PontuacaoComentario> existByComentarioAndUsuario(Long idComentario, Long idusuario);

    @Modifying
    @Query(value="update tb_pontuacao_comentario set pontuacao = ?3 where id_comentario = ?1 AND id_usuario = ?2", nativeQuery = true)
    int setPontuacaoFor(Long idcomentario, Long idusuario, int pontuacao);
}
