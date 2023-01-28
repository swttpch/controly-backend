package controly.entity;
import javax.persistence.*;

import javax.validation.constraints.NotNull;

@Entity
@Table(name="tbTopic")
public class TopicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTopic;

    @NotNull
    private String name;

    @NotNull
    private String about;

    public Long getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(Long idTopic) {
        this.idTopic = idTopic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}