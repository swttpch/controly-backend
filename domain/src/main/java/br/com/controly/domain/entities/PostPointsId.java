package br.com.controly.domain.entities;

import lombok.Data;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
@Data
public class PostPointsId  implements Serializable {
    private Long idPost;
    private Long idUser;
}
