package controly.repository;

import controly.entities.PostagemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostagemRepository extends JpaRepository<PostagemEntity, Long> {

    PostagemEntity findByIdPostagem(Long id);

    List<PostagemEntity> findByDonoIdUsuario(Long idUser);

    int deleteByIdPostagem(Long id);

}
