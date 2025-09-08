package br.com.controly.jpa;

import br.com.controly.domain.entities.Topics;
import br.com.controly.domain.entities.TopicFollowers;
import br.com.controly.domain.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicHasFollowersRepository extends JpaRepository<TopicFollowers, Long > {
    @Query("SELECT COUNT(tf.follower) FROM TopicHasFollowersEntity tf WHERE tf.topic.idTopic = :idTopic")
    Integer countFollowersATopicHas(Long idTopic);

    @Query("SELECT s FROM TopicHasFollowersEntity s WHERE s.topic.idTopic = :idTopic AND s.follower.idUser = :idUser")
    TopicFollowers findByTopicIdTopicAndUserIdUser(Long idTopic, Long idUser);

    @Query(value = "SELECT s FROM TopicHasFollowersEntity s WHERE s.topic = :topic AND s.follower = :user")
    Optional<TopicFollowers> userFollowsTopic(Topics topic, Users user);

    List<TopicFollowers> findByFollowerIdUser(Long idUser);
}
