package br.com.controly.domain.entities;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;


@Embeddable
@Data
public class CommentPointsId implements Serializable {
    private Long commentId;
    private Long userId;
}
