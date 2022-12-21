package controly.repository;

import controly.entity.CommentEntity;
import controly.entity.CommentPointsEntity;
import controly.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface CommentPointsRepository extends JpaRepository<CommentPointsEntity, Long> {
    Optional<List<CommentPointsEntity>> findByComment(CommentEntity comment);

    @Query(value = "SELECT m FROM CommentPointsEntity m WHERE m.comment = ?1 AND m.user = ?2")
    Optional<CommentPointsEntity> existByCommentAndUser(CommentEntity comment, UserEntity user);

    @Transactional
    @Modifying
    @Query("DELETE FROM CommentPointsEntity WHERE comment.idComment = :idComment and user.idUser = :idUser")
    void deleteByComment_idComment(Long idComment, Long idUser);

}
