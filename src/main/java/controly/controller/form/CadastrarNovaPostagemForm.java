package controly.controller.form;

import controly.model.entity.PostagemEntity;
import controly.model.entity.TopicoEntity;
import controly.model.entity.UsuarioEntity;
import controly.model.service.TopicoService;
import controly.model.service.UsuarioService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CadastrarNovaPostagemForm {
    private TopicoService topicoService = new TopicoService();
    private UsuarioService usuarioService = new UsuarioService();

    private String titulo;

    private String conteudo;

    private Long idTopico;

    private int idUsuario;

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

    public Long getTopico() {
        return idTopico;
    }

    public void setTopico(Long topico) {
        this.idTopico = topico;
    }

    public Long getIdTopico() {
        return idTopico;
    }

    public void setIdTopico(Long idTopico) {
        this.idTopico = idTopico;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
/*
    public PostagemEntity converterPostagem(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        TopicoEntity topico = topicoService.buscarTopicoPeloId(idTopico).get();
        UsuarioEntity usuario = usuarioService.buscarUsuarioPorId((long) idUsuario).get();
        return new PostagemEntity(conteudo, titulo, topico, usuario);
    }

*/
}
