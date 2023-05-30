package codelabz.repository;

import codelabz.entity.CommentEntity;
import codelabz.entity.CommentPointsEntity;
import codelabz.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface CommentPointsRepository extends JpaRepository<CommentPointsEntity, Long> {
    @Query(value = "SELECT m FROM CommentPointsEntity m WHERE m.comment = ?1 AND m.user = ?2")
    Optional<CommentPointsEntity> existByCommentAndUser(CommentEntity comment, UserEntity user);


}
