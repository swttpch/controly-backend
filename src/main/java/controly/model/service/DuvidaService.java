package controly.model.service;

import controly.controller.form.Postagem;
import controly.strategy.Ipostagem;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DuvidaService implements Ipostagem {

    @Override
    public ResponseEntity enviarPostagem(Postagem post) {
        return null;
    }
}
