package br.com.controly.jpa;

import br.com.controly.domain.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    List<PostEntity> findByOwnerIdUser(Long idUser);

    List<PostEntity> findByTopicIdTopic(Long idTopic);
}
