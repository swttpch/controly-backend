package controly.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "tbPost")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbPost")
@SecondaryTable(name= "tbDoubtsAnswer", pkJoinColumns = @PrimaryKeyJoinColumn(name = "idPost"))
@Data
public class PostEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost;
    private String content;
    @ManyToOne @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    private UserEntity owner;

    private String title;

    @OneToOne @JoinColumn(name="idTopic", referencedColumnName = "idTopic")
    private TopicEntity topic;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPost")
    private List<CommentEntity> comments;

    @Embedded
    private DoubtsAnswerEntity doubtsAnswerEntity;

    @Column(name = "createdIn")
    private LocalDateTime createdIn;
    @Column(name = "updatedIn") @Nullable
    private LocalDateTime updatedIn;

    @OneToMany(mappedBy = "post",cascade=CascadeType.ALL) @JsonIgnore
    private Set<PostPointsEntity> postPointEntities = new HashSet<>();

    @JsonProperty
    public int getPoints(){
        return postPointEntities.stream().mapToInt(PostPointsEntity::getPoints).sum();
    }

    public void setAnswer(CommentEntity answer){
        doubtsAnswerEntity
                .setAnswer(answer)
                .setSolved(true)
                .setSolvedIn(LocalDateTime.now());
    }

    public PostEntity initDoubt(){
        doubtsAnswerEntity = new DoubtsAnswerEntity().setSolved(false);
        return this;
    }
}
