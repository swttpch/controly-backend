package controly.strategy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import controly.entity.CommentEntity;
import controly.entity.PostEntity;
import controly.entity.TopicEntity;
import controly.entity.UserEntity;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@NoArgsConstructor
public class Comentario extends Postagem {

    private String conteudo;
    private Long idPostagem;
    private Long idUsuario;

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    @Override
    public Long getIdPostagem() {
        return idPostagem;
    }

    public void setIdPostagem(Long idPostagem) {
        this.idPostagem = idPostagem;
    }

    @Override
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override @JsonIgnore
    public CommentEntity converterComentario(PostEntity postagem, UserEntity usuario) {
        CommentEntity comentario = new CommentEntity();
        comentario.setContent(conteudo);
        comentario.setCreatedIn(LocalDateTime.now());
        comentario.setOwner(usuario);
        comentario.setPost(postagem);
        return comentario;
    }


    // ----- TRASH CODE BELOW -----

    @Override @JsonIgnore
    public Long getIdTopico() {
        return null;
    }

    @Override @JsonIgnore
    public PostEntity converterPostagem(TopicEntity topico, UserEntity usuario) {
        return null;
    }
}
