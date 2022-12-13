package controly.entities;
import javax.persistence.*;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Entity
@Table(name="tbTopico")
@Data
public class TopicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTopico;

    @NotNull
    private String nome;

    @NotNull
    private String descricao;

}