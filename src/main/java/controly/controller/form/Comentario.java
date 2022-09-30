package controly.controller.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import controly.model.entity.ComentarioEntity;
import controly.model.entity.PostagemEntity;
import controly.model.entity.TopicoEntity;
import controly.model.entity.UsuarioEntity;
import controly.strategy.Ipostagem;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@NoArgsConstructor
public class Comentario extends Postagem{

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
    public ComentarioEntity converterPostagem(PostagemEntity postagem, UsuarioEntity usuario) {
        ComentarioEntity comentario = new ComentarioEntity();
        comentario.setConteudo(conteudo);
        comentario.setCriadoEm(LocalDateTime.now());
        comentario.setDono(usuario);
        comentario.setPostagem(postagem);
        return comentario;
    }


    // ----- TRASH CODE BELOW -----

    @Override @JsonIgnore
    public Long getIdTopico() {
        return null;
    }

    @Override @JsonIgnore
    public PostagemEntity converterPostagem(TopicoEntity topico, UsuarioEntity usuario) {
        return null;
    }
}
