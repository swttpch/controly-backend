package controly.controller.form;

import controly.model.entity.ComentarioEntity;
import controly.model.entity.PostagemEntity;
import controly.model.entity.TopicoEntity;
import controly.model.entity.UsuarioEntity;


import java.time.LocalDateTime;
public class Discussao extends Postagem{
    private String titulo;
    private String conteudo;
    private Long idUsuario;
    private Long idTopico;

    @Override
    public PostagemEntity converterPostagem(TopicoEntity topico, UsuarioEntity usuario){
        PostagemEntity postagem = new PostagemEntity();
        postagem.setTitulo(titulo);
        postagem.setConteudo(conteudo);
        postagem.setCriadoEm(LocalDateTime.now());
        postagem.setDono(usuario);
        postagem.setTopico(topico);
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
    public Long getIdPostagem() {
        return null;
    }

    @Override
    public ComentarioEntity converterPostagem(PostagemEntity postagem, UsuarioEntity usuario) {
        return null;
    }
}
