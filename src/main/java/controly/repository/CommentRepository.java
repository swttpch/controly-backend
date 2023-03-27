package controly.repository;

import controly.entity.CommentEntity;
import controly.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByPost(PostEntity post);
}
