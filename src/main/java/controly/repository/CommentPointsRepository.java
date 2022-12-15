package controly.repository;

import controly.entity.CommentEntity;
import controly.entity.CommentPointsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface CommentPointsRepository extends JpaRepository<CommentPointsEntity, Long> {
    Optional<List<CommentPointsEntity>> findByComment(CommentEntity comment);

    @Query(value = "SELECT m FROM CommentPointsEntity m WHERE m.comment.idComment = ?1 AND m.user.idUser = ?2")
    Optional<CommentPointsEntity> existByCommentAndUser(Long idComment, Long idUser);

    @Transactional
    @Modifying
    @Query("DELETE FROM CommentPointsEntity WHERE comment.idComment = :idComment and user.idUser = :idUser")
    void deleteByComment_idComment(Long idComment, Long idUser);

}
