package controly.model.service;

import controly.model.Ipostagem;
import controly.model.entity.PostagemEntity;
import org.springframework.http.ResponseEntity;

public class DuvidaService implements Ipostagem {

    @Override
    public ResponseEntity construirPostagem(PostagemEntity dados) {
        return null;
    }
}
