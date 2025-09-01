package br.com.controly.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity @Table(name = "tbComment") @NoArgsConstructor @AllArgsConstructor
public class CommentEntity implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "idComment")
    private Long idComment;
    @Column(name = "content")
    private String content;
    @Column(name = "createdIn")
    private LocalDateTime createdIn;
    @Column(name = "updatedIn") @Nullable
    private LocalDateTime updatedIn;
    @ManyToOne
    @JoinColumn(name = "idPost", referencedColumnName = "idPost")@JsonIgnore
    private PostEntity post;

    @OneToMany(mappedBy = "comment",cascade=CascadeType.ALL) @JsonIgnore
    private Set<CommentPointsEntity> likes = new HashSet<>();

    @ManyToOne @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    private UserEntity owner;

    @JsonProperty
    public int getLikes(){
        return likes.size();
    }

    public Long getIdComment() {
        return idComment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }
}
