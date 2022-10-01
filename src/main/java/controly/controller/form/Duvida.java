package controly.controller.form;

import controly.strategy.Ipostagem;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class Duvida{
    private String titulo;
    private String conteudo;
    private Long idUsuario;
    private Long idTopico;

    public Duvida(String titulo, String conteudo, Long idUsuario, Long idTopico) {
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.idUsuario = idUsuario;
        this.idTopico = idTopico;
    }

}
