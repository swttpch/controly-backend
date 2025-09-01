package br.com.controly.domain.entities;

import javax.persistence.*;
@Entity
@Table(name = "tbPostPoints")
public class PostPointsEntity {
    @EmbeddedId
    final private PostPointsId id = new PostPointsId();

    @ManyToOne
    @MapsId("idPost") @JoinColumn(name = "idPost")
    private PostEntity post;

    @ManyToOne
    @MapsId("idUser") @JoinColumn(name = "idUser")
    private UserEntity user;

    private int points;

    public PostEntity getPost() {
        return post;
    }

    public UserEntity getUser() {
        return user;
    }

    public int getPoints() {
        return points;
    }

    public PostPointsEntity setPost(PostEntity post) {
        this.post = post;
        return this;
    }

    public PostPointsEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public PostPointsEntity setPoints(int points) {
        this.points = points;
        return this;
    }
}
