package br.com.controly.domain.entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class CommentPoints {

    @EmbeddedId
    final private CommentPointsId id = new CommentPointsId();

    @ManyToOne
    @MapsId @JoinColumn(name = "comment_id")
    private Comments comment;

    @ManyToOne
    @MapsId @JoinColumn(name = "user_id")
    private Users user;
}
