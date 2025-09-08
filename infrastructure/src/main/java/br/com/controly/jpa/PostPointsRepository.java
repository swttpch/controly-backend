package br.com.controly.jpa;

import br.com.controly.domain.entities.PostPoints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface PostPointsRepository extends JpaRepository<PostPoints, Long> {
    @Query(value = "SELECT m FROM PostPointsEntity m WHERE m.post.idPost = ?1 AND m.user.idUser = ?2")
    Optional<PostPoints> existByPostAndUser(Long idPost, Long idUser);

    Long countByPost_IdPost(Long idPost);
}

