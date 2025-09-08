package br.com.controly.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;


@Data
@NoArgsConstructor
public class CreateCommentDTO {
    @NotNull
    private String content;
    @NotNull
    private Long idUser;

    @NotNull
    private Long idPost;
}
