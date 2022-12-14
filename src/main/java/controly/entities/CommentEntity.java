package controly.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity @Table(name = "tbComment") @Data @NoArgsConstructor @AllArgsConstructor
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

    public void deleteLike(Long idUsuario){
        likes.removeIf(commentPointsEntity -> Objects.equals(commentPointsEntity.getUser().getIdUser(), idUsuario));
    }
}
