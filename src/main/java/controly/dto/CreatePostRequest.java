package controly.dto;

import controly.entity.PostEntity;
import controly.entity.TopicEntity;
import controly.entity.UserEntity;

import javax.validation.constraints.NotNull;

public class CreatePostRequest {
    @NotNull
    private String title;
    @NotNull
    private String content;
    @NotNull
    private Long idUser;
    @NotNull
    private Long idTopic;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(Long idTopic) {
        this.idTopic = idTopic;
    }

    public PostEntity convert(TopicEntity topico, UserEntity usuario){
        PostEntity post = new PostEntity();
        post.setTitle(title);
        post.setContent(content);
        post.setOwner(usuario);
        post.setTopic(topico);
        return post;
    }
}
