package controly.strategy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import controly.entity.CommentEntity;
import controly.entity.PostEntity;
import controly.entity.TopicEntity;
import controly.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class Duvida extends Postagem {
    private String titulo;
    private String conteudo;
    private Long idUsuario;
    private Long idTopico;

    public Duvida(String titulo, String conteudo, Long idUsuario, Long idTopico) {
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.idUsuario = idUsuario;
        this.idTopico = idTopico;
    }

    public Duvida() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    @Override
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public Long getIdTopico() {
        return idTopico;
    }

    @Override
    public PostEntity converterPostagem(TopicEntity topico, UserEntity usuario) {
        PostEntity postagem = new PostEntity();
        postagem.setTitle(titulo);
        postagem.setContent(conteudo);
        postagem.setOwner(usuario);
        postagem.setTopic(topico);
        return postagem;
    }

    public void setIdTopico(Long idTopico) {
        this.idTopico = idTopico;
    }

    // ----- TRASH CODE BELOW -----
    @Override
    public CommentEntity converterComentario(PostEntity postagem, UserEntity usuario) {
        return null;
    }

    @Override
    @JsonIgnore
    public Long getIdPostagem() {
        return null;
    }
}
