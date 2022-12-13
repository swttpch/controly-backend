package controly.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizarUsuarioRequest {

    private String nome;
    private String apelido;
    private String descricao;

}
