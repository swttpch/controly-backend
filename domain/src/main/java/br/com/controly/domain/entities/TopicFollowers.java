package br.com.controly.domain.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class TopicFollowers extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users follower;

    @ManyToOne
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    private Topics topic;
}
