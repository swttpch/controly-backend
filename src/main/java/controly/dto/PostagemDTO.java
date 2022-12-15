package controly.dto;

import controly.entity.UserEntity;
import controly.entity.PostEntity;
import controly.entity.TopicEntity;

import javax.persistence.Id;

public class PostagemDTO {

    @Id
    private Long idPostagem = new PostEntity().getIdPost();

    private String titulo = new PostEntity().getTitle();

    private String conteudo = new PostEntity().getContent();

    private UserEntity dono = new PostEntity().getOwner();

    private TopicEntity topico = new PostEntity().getTopic();

    private Integer pontuacaoPostagem = new PostEntity().getPoints();

    public Long getIdPostagem() {
        return idPostagem;
    }

    public void setIdPostagem(Long idPostagem) {
        this.idPostagem = idPostagem;
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

    public UserEntity getDono() {
        return dono;
    }

    public void setDono(UserEntity dono) {
        this.dono = dono;
    }

    public TopicEntity getTopico() {
        return topico;
    }

    public void setTopico(TopicEntity topico) {
        this.topico = topico;
    }

    public Integer getPontuacaoPostagem() {
        return pontuacaoPostagem;
    }

    public void setPontuacaoPostagem(Integer pontuacaoPostagem) {
        this.pontuacaoPostagem = pontuacaoPostagem;
    }
}
