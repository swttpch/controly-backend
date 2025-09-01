package br.com.controly.dtos;

import br.com.controly.domain.entities.PostEntity;
import br.com.controly.domain.entities.TopicEntity;
import br.com.controly.domain.entities.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
@Getter
@Setter
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

    public PostEntity convert(TopicEntity topic, UserEntity user){
        PostEntity post = new PostEntity();
        post.setTitle(title);
        post.setContent(content);
        post.setOwner(user);
        post.setTopic(topic);
        return post;
    }
}
