package controly.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "tbTopicHasFollowers")
@Data
public class TopicHasFollowersEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "idUser")
    @JsonIgnore
    private UserEntity follower;

    @ManyToOne
    @JoinColumn(name = "id_topic", referencedColumnName = "idTopic")
    private TopicEntity topic;

}
