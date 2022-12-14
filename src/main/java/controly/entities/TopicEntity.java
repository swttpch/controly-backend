package controly.entities;
import javax.persistence.*;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Entity
@Table(name="tbTopic")
@Data
public class TopicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTopic;

    @NotNull
    private String name;

    @NotNull
    private String about;

}