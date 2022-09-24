package controly.model;

import controly.model.entity.PostagemEntity;
import org.springframework.http.ResponseEntity;

public interface Ipostagem {
    ResponseEntity construirPostagem(PostagemEntity dados);
}
