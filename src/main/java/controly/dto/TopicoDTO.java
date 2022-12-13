package controly.dto;

import controly.entities.TopicoEntity;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
public class TopicoDTO {

    @Id
    private Long idTopico = new TopicoEntity().getIdTopico();

    @NotNull
    private String nome = new TopicoEntity().getNome();

    @NotNull
    private String descricao = new TopicoEntity().getDescricao();

    private Integer seguidores;

}
