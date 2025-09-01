package br.com.controly.jpa;

import br.com.controly.domain.entities.CommentEntity;
import br.com.controly.domain.entities.CommentPointsEntity;
import br.com.controly.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface CommentPointsRepository extends JpaRepository<CommentPointsEntity, Long> {
    @Query(value = "SELECT m FROM CommentPointsEntity m WHERE m.comment = ?1 AND m.user = ?2")
    Optional<CommentPointsEntity> existByCommentAndUser(CommentEntity comment, UserEntity user);


    Integer countByCommentAndUser(CommentEntity comment, UserEntity user);

    Integer countByComment(CommentEntity comment);


}
