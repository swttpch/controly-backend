package controly.strategy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import controly.entities.CommentEntity;
import controly.entities.PostEntity;
import controly.entities.TopicEntity;
import controly.entities.UserEntity;

public class Discussao extends Postagem {
    private String titulo;
    private String conteudo;
    private Long idUsuario;
    private Long idTopico;

    @Override
    public PostEntity converterPostagem(TopicEntity topico, UserEntity usuario){
        PostEntity postagem = new PostEntity();
        postagem.setTitle(titulo);
        postagem.setContent(conteudo);
        postagem.setOwner(usuario);
        postagem.setTopic(topico);
        return postagem;
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

    public void setIdTopico(Long idTopico) {
        this.idTopico = idTopico;
    }

    // ----- TRASH CODE BELOW -----

    @Override
    @JsonIgnore
    public Long getIdPostagem() {
        return null;
    }

    @Override
    public CommentEntity converterComentario(PostEntity postagem, UserEntity usuario) {
        return null;
    }
}
