package controly.repository;

import controly.model.entity.PontuacaoPostagem;
import controly.model.entity.PostagemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface PontuacaoPostagemRepository extends JpaRepository<PontuacaoPostagem, Long> {

    Optional<List<PontuacaoPostagem>> findByPostagem(PostagemEntity postagem);

    @Query(value = "SELECT m FROM PontuacaoPostagem m WHERE m.postagem.idPostagem = ?1 AND m.usuario.idUsuario = ?2")
    Optional<PontuacaoPostagem> existByPostagemAndUsuario(Long idpostagem, Long idusuario);

    @Transactional @Modifying
    @Query(value="UPDATE PontuacaoPostagem p SET p.pontuacao = ?3 WHERE p.postagem.idPostagem = ?1 AND p.usuario.idUsuario = ?2")
    void setPontuacaoFor(Long idpostagem, Long idusuario, int pontuacao);

    @Query
    Optional<PontuacaoPostagem> findByPostagemIdPostagemAndUsuarioIdUsuario(Long idpostagem, Long idusuario);
}

