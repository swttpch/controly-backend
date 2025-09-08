package br.com.controly.jpa;

import br.com.controly.domain.entities.Topics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topics,Long> {
}