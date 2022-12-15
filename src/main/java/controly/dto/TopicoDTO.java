package controly.dto;

import controly.entities.TopicEntity;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class TopicoDTO {

    @Id
    private Long idTopico = new TopicEntity().getIdTopic();

    @NotNull
    private String nome = new TopicEntity().getName();

    @NotNull
    private String descricao = new TopicEntity().getAbout();

    private Integer seguidores;

    public Long getIdTopico() {
        return idTopico;
    }

    public void setIdTopico(Long idTopico) {
        this.idTopico = idTopico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(Integer seguidores) {
        this.seguidores = seguidores;
    }
}
