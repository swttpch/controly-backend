package br.com.controly.domain.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@SecondaryTable(name= "answers", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
@EqualsAndHashCode(callSuper = true)
public class Posts extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @ManyToOne @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users owner;

    private String title;

    @OneToOne @JoinColumn(name="topic_id", referencedColumnName = "id")
    private Topics topic;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Comments> comments;

    @Embedded
    private Answers doubtsAnswerEntity;

    @OneToMany(mappedBy = "post",cascade=CascadeType.ALL)
    private Set<PostPoints> postPoints = new HashSet<>();

    public void initDoubt(){
        doubtsAnswerEntity = new Answers();
    }

    public boolean isDoubt(){
        return doubtsAnswerEntity != null;
    }
}
