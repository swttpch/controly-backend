package controly.controller.form;

import controly.model.entity.PostagemEntity;
import controly.model.entity.TopicoEntity;
import controly.model.entity.UsuarioEntity;

import java.time.LocalDateTime;


public class CadastrarNovaPostagemForm {

    private String titulo;

    private String conteudo;

    private Long idTopico;

    private Long idUsuario;

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

    public Long getIdTopico() {
        return idTopico;
    }

    public void setIdTopico(Long idTopico) {
        this.idTopico = idTopico;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public PostagemEntity converterPostagem(TopicoEntity topico, UsuarioEntity usuario){
        PostagemEntity postagem = new PostagemEntity();
        postagem.setTitulo(titulo);
        postagem.setConteudo(conteudo);
        postagem.setCriadoEm(LocalDateTime.now());
        postagem.setDono(usuario);
        postagem.setTopico(topico);
        return postagem;
    }
}
