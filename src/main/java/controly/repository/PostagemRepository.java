package controly.repository;

import controly.model.entity.PostagemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostagemRepository extends JpaRepository<PostagemEntity, Long> {

}
