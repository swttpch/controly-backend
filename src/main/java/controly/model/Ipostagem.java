package controly.model;

import org.springframework.http.ResponseEntity;

public interface Ipostagem {
    ResponseEntity construirPostagem(Object dados);
}
