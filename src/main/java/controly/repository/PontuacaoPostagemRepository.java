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

    @Query(value = "select * from tb_pontuacao_postagem where postagem_id_postagem = ?1 AND usuario_id_usuario = ?2", nativeQuery = true)
    Optional<PontuacaoPostagem> existByPostagemAndUsuario(Long idpostagem, Long idusuario);

//    @Modifying
    @Query(value="update tb_pontuacao_postagem set pontuacao = ?3 where postagem_id_postagem = ?1 AND usuario_id_usuario = ?2", nativeQuery = true)
    int setPontuacaoFor(Long idpostagem, Long idusuario, int pontuacao);
}
