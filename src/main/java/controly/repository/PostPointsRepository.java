package controly.repository;

import controly.entity.PostPointsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface PostPointsRepository extends JpaRepository<PostPointsEntity, Long> {
    @Query(value = "SELECT m FROM PostPointsEntity m WHERE m.post.idPost = ?1 AND m.user.idUser = ?2")
    Optional<PostPointsEntity> existByPostAndUser(Long idPost, Long idUser);

    Long countByPost_IdPost(Long idPost);
}

