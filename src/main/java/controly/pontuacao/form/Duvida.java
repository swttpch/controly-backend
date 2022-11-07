package controly.pontuacao.form;

import controly.postagem.entities.Postagem;
import controly.comentario.entities.ComentarioEntity;
import controly.postagem.entities.PostagemEntity;
import controly.topico.entities.TopicoEntity;
import controly.perfilAndUsuario.entities.UsuarioEntity;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@NoArgsConstructor
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
    public PostagemEntity converterPostagem(TopicoEntity topico, UsuarioEntity usuario) {
        PostagemEntity postagem = new PostagemEntity();
        postagem.setTitulo(titulo);
        postagem.setConteudo(conteudo);
        postagem.setCriadoEm(LocalDateTime.now());
        postagem.setDono(usuario);
        postagem.setTopico(topico);
        return postagem;
    }

    public void setIdTopico(Long idTopico) {
        this.idTopico = idTopico;
    }

    // ----- TRASH CODE BELOW -----
    @Override
    public ComentarioEntity converterPostagem(PostagemEntity postagem, UsuarioEntity usuario) {
        return null;
    }

    @Override
    public Long getIdPostagem() {
        return null;
    }
}
