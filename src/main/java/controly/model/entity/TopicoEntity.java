package controly.model.entity;

import org.springframework.context.annotation.Conditional;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="tb_topico")
public class TopicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    public TopicoEntity() {
    }

    public TopicoEntity(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
