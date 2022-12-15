package controly.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
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

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TopicEntity getTopic() {
        return topic;
    }

    public void setTopic(TopicEntity topic) {
        this.topic = topic;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }

    public DoubtsAnswerEntity getDoubtsAnswerEntity() {
        return doubtsAnswerEntity;
    }

    public void setDoubtsAnswerEntity(DoubtsAnswerEntity doubtsAnswerEntity) {
        this.doubtsAnswerEntity = doubtsAnswerEntity;
    }

    public LocalDateTime getCreatedIn() {
        return createdIn;
    }

    public void setCreatedIn(LocalDateTime createdIn) {
        this.createdIn = createdIn;
    }

    @Nullable
    public LocalDateTime getUpdatedIn() {
        return updatedIn;
    }

    public void setUpdatedIn(@Nullable LocalDateTime updatedIn) {
        this.updatedIn = updatedIn;
    }

    public Set<PostPointsEntity> getPostPointEntities() {
        return postPointEntities;
    }

    public void setPostPointEntities(Set<PostPointsEntity> postPointEntities) {
        this.postPointEntities = postPointEntities;
    }

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
