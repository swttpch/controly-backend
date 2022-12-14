package controly.repository;

import controly.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    CommentEntity findByIdComment(Long id);

    List<CommentEntity> findByPostIdPost(Long idPost);
}
