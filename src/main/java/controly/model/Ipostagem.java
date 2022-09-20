package controly.model;

import controly.model.entity.Postagem;
import org.springframework.http.ResponseEntity;

public interface Ipostagem {
    ResponseEntity construirPostagem(Postagem dados);
}
