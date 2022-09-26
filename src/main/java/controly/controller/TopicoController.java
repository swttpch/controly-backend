package controly.controller;

import controly.model.entity.TopicoEntity;
import controly.model.service.TopicoService;
import controly.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    TopicoRepository topicoRepository;

    TopicoEntity topico = new TopicoEntity("Igor");

    @GetMapping
    public ResponseEntity<List<TopicoEntity>> getTopicos(){
        List<TopicoEntity> lista = topicoRepository.findAll();
        return lista.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(lista);
           }

    @PostMapping
    public ResponseEntity<TopicoEntity> post(@RequestBody TopicoEntity topicoEntity) {
        topicoRepository.save(topicoEntity);
        List<TopicoEntity> lista = topicoRepository.findAll();
        return ResponseEntity.status(201).body(topicoEntity);
    }

}
