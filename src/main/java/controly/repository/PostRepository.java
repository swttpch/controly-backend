package controly.repository;

import controly.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    List<PostEntity> findByOwnerIdUser(Long idUser);

    List<PostEntity> findByTopicIdTopic(Long idTopic);
}
