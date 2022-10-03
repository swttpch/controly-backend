package controly.model.service;

import controly.model.entity.TopicoEntity;
import controly.model.entity.UsuarioEntity;
import controly.repository.TopicoRepository;
import controly.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    UsuarioService usuarioService;


    public ResponseEntity<List<TopicoEntity>> getTopicos() {
        List<TopicoEntity> lista = topicoRepository.findAll();

        return lista.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(lista);
    }

    public ResponseEntity<TopicoEntity> postTopicos(@RequestBody TopicoEntity topicoEntity) {
        topicoRepository.save(topicoEntity);
        List<TopicoEntity> lista = topicoRepository.findAll();
        return ResponseEntity.status(201).body(topicoEntity);
    }

    public ResponseEntity<Map<Object, Object>> getTopicosMostFollowed() {

        Map<Object, Object> mapTopicos = new HashMap<>();

        return ResponseEntity.status(201).body(mapTopicos);
    }
}
