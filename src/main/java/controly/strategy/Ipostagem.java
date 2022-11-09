package controly.strategy;

import controly.modules.postagem.entities.Postagem;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface Ipostagem {

    ResponseEntity enviarPostagem(Postagem post);


}
