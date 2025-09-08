package br.com.controly.jpa;

import br.com.controly.domain.entities.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<Posts, Long> {
    List<Posts> findByOwnerIdUser(Long idUser);

    List<Posts> findByTopicIdTopic(Long idTopic);
}
