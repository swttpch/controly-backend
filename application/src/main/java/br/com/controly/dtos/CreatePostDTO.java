package br.com.controly.dtos;

import br.com.controly.domain.entities.Posts;
import br.com.controly.domain.entities.Topics;
import br.com.controly.domain.entities.Users;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
@Data
@NoArgsConstructor
public class CreatePostDTO {
    @NotNull
    private String title;
    @NotNull
    private String content;
    @NotNull
    private Long idUser;
    @NotNull
    private Long idTopic;

    public Posts convert(Topics topic, Users user){
        Posts post = new Posts();
        post.setTitle(title);
        post.setContent(content);
        post.setOwner(user);
        post.setTopic(topic);
        return post;
    }
}
