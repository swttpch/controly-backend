package br.com.controly.jpa;

import br.com.controly.domain.entities.Comments;
import br.com.controly.domain.entities.CommentPoints;
import br.com.controly.domain.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface CommentPointsRepository extends JpaRepository<CommentPoints, Long> {
    @Query(value = "SELECT m FROM CommentPointsEntity m WHERE m.comment = ?1 AND m.user = ?2")
    Optional<CommentPoints> existByCommentAndUser(Comments comment, Users user);


    Integer countByCommentAndUser(Comments comment, Users user);

    Integer countByComment(Comments comment);


}
