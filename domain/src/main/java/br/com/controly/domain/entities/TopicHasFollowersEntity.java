package br.com.controly.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "tbTopicHasFollowers")
public class TopicHasFollowersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "idUser")
    @JsonIgnore
    private UserEntity follower;

    @ManyToOne
    @JoinColumn(name = "id_topic", referencedColumnName = "idTopic")
    private TopicEntity topic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getFollower() {
        return follower;
    }

    public TopicHasFollowersEntity setFollower(UserEntity follower) {
        this.follower = follower;
        return this;
    }

    public TopicEntity getTopic() {
        return topic;
    }

    public TopicHasFollowersEntity setTopic(TopicEntity topic) {
        this.topic = topic;
        return this;
    }
}
