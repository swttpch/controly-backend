package controly.entities;

import javax.persistence.*;

@Entity
@Table(name = "tbCommentPoints")
public class CommentPointsEntity {
    @EmbeddedId
    final private CommentPointsId id = new CommentPointsId();

    @ManyToOne
    @MapsId("idComment") @JoinColumn(name = "idComment")
    private CommentEntity comment;

    @ManyToOne
    @MapsId("idUser") @JoinColumn(name = "idUser")
    private UserEntity user;

    public CommentEntity getComment() {
        return comment;
    }

    public CommentPointsEntity setComment(CommentEntity comment) {
        this.comment = comment;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public CommentPointsEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
