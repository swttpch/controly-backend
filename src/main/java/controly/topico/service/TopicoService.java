package controly.topico.service;

import controly.topico.entities.TopicoEntity;
import controly.service.ValidationService;
import controly.topico.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import javax.transaction.Transactional;

@Service
public class TopicoService {
    @Autowired
    final private TopicoRepository topicoRepository;
    @Autowired
    final private ValidationService validation;

    public TopicoService(TopicoRepository topicoRepository, ValidationService validation) {
        this.topicoRepository = topicoRepository;
        this.validation = validation;
    }

    public ResponseEntity<List<TopicoEntity>> getTopicos() {
        List<TopicoEntity> lista = topicoRepository.findAll();

        return lista.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(lista);
    }

    @Transactional
    public ResponseEntity<TopicoEntity> getTopicoById(Long id) {
        if (validation.existsTopico(id)) return ResponseEntity.status(404).build();
        return ResponseEntity.status(200).body(topicoRepository.findByIdTopico(id));
    }

    @Transactional
    public ResponseEntity<TopicoEntity> postTopicos(@RequestBody TopicoEntity topicoEntity) {
        topicoRepository.save(topicoEntity);
        return ResponseEntity.status(201).body(topicoEntity);
    }
}