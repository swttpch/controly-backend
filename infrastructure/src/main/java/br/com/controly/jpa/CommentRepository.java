package br.com.controly.jpa;

import br.com.controly.domain.entities.Comments;
import br.com.controly.domain.entities.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comments, Long> {
    List<Comments> findByPost(Posts post);
}
