package br.com.controly.jpa;

import br.com.controly.domain.entities.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<TopicEntity,Long> {
}