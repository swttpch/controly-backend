package controly.modules.postagem.repository;

import controly.modules.postagem.entities.PostagemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface PostagemRepository extends JpaRepository<PostagemEntity, Long> {

    PostagemEntity findByIdPostagem(Long id);

    List<PostagemEntity> findByDonoIdUsuario(Long idUser);

    int deleteByIdPostagem(Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM PontuacaoComentario tb WHERE tb.comentario.idComentario = ?1")
    void deleteByComentario_idComentario(Long idComentario);

}
