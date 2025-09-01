package br.com.controly.jpa;

import br.com.controly.domain.entities.CommentEntity;
import br.com.controly.domain.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByPost(PostEntity post);
}
