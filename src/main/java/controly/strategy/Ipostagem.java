package controly.strategy;

import controly.controller.form.Postagem;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public interface Ipostagem {
    @Transactional
    ResponseEntity enviarPostagem(Postagem post);
}
