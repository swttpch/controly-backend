package br.com.controly.domain.entities;

import lombok.Data;

import jakarta.persistence.*;


@Entity
@Data
public class PostPoints {
    @EmbeddedId
    final private PostPointsId id = new PostPointsId();

    @ManyToOne
    @MapsId
    @JoinColumn(name = "post_id")
    private Posts post;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private Users user;

    private int points;
}
