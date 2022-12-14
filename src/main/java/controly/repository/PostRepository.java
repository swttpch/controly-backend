package controly.repository;

import controly.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    PostEntity findByIdPost(Long id);

    List<PostEntity> findByOwnerIdUser(Long idUser);

    int deleteByIdPost(Long id);

}
