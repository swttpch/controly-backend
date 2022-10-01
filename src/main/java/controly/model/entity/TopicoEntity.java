package controly.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

}

