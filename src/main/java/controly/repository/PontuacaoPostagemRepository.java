package controly.repository;

import controly.model.entity.PontuacaoPostagem;
import controly.model.entity.PostagemEntity;
import controly.model.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PontuacaoPostagemRepository extends JpaRepository<PontuacaoPostagem, Long> {

    Optional<List<PontuacaoPostagem>> findByPostagem(PostagemEntity postagem);

    @Query(value = "select m from PontuacaoPostagem m where m.postagem.idPostagem = ?1 AND m.usuario.idUsuario = ?2")
    Optional<PontuacaoPostagem> existByPostagemAndUsuario(Long idpostagem, Long idusuario);

    @Modifying
    @Query(value="update PontuacaoPostagem p set p.pontuacao = ?3 where p.postagem.idPostagem = ?1 AND p.usuario.idUsuario = ?2")
    int setPontuacaoFor(Long idpostagem, Long idusuario, int pontuacao);
}
