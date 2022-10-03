package controly.model.entity;
import javax.persistence.*;
import org.springframework.context.annotation.Conditional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Entity
@Table(name="tbTopico")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TopicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTopico;

    @NotNull
    private String nome;

    @NotNull
    private String descricao;

    public TopicoEntity() {
    }

    public TopicoEntity(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return idTopico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}

