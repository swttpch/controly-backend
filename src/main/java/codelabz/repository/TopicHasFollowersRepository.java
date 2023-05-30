package codelabz.repository;

import codelabz.entity.TopicEntity;
import codelabz.entity.TopicHasFollowersEntity;
import codelabz.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicHasFollowersRepository extends JpaRepository<TopicHasFollowersEntity, Long > {
    @Query("SELECT COUNT(tf.follower) FROM TopicHasFollowersEntity tf WHERE tf.topic.idTopic = :idTopic")
    Integer countFollowersATopicHas(Long idTopic);

    @Query("SELECT s FROM TopicHasFollowersEntity s WHERE s.topic.idTopic = :idTopic AND s.follower.idUser = :idUser")
    TopicHasFollowersEntity findByTopicIdTopicAndUserIdUser(Long idTopic, Long idUser);

    @Query(value = "SELECT s FROM TopicHasFollowersEntity s WHERE s.topic = :topic AND s.follower = :user")
    Optional<TopicHasFollowersEntity> userFollowsTopic(TopicEntity topic, UserEntity user);

    List<TopicHasFollowersEntity> findByFollowerIdUser(Long idUser);
}
