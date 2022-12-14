package controly.repository;

import controly.entities.PostPointsEntity;
import controly.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostPointsRepository extends JpaRepository<PostPointsEntity, Long> {

    Optional<List<PostPointsEntity>> findByPost(PostEntity postagem);

    @Query(value = "SELECT m FROM PostPointsEntity m WHERE m.post.idPost = ?1 AND m.user.idUser = ?2")
    Optional<PostPointsEntity> existByPostAndUser(Long idPost, Long idUser);

    @Query(value = "UPDATE PostPointsEntity p SET p.points = ?3 WHERE p.post.idPost = ?1 AND p.user.idUser = ?2")
    void setPointsFor(Long idPost, Long idUser, int point);


    @Query
    Optional<PostPointsEntity> findByPostIdPostAndUserIdUser(Long idPost, Long idUser);
}

