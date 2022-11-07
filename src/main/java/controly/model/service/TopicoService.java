package controly.model.service;

import controly.model.entity.TopicoEntity;
import controly.model.entity.TopicoHasSeguidoresEntity;
import controly.repository.TopicoHasSeguidoresRepositoy;
import controly.repository.TopicoRepository;
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
    private TopicoHasSeguidoresRepositoy topicoHasSeguidoresRepositoy;

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
        if (!validation.existsTopico(id)) return ResponseEntity.status(404).build();
        return ResponseEntity.status(200).body(topicoRepository.findByIdTopico(id));
    }

    public ResponseEntity<List<TopicoHasSeguidoresEntity>> getTopicosByIdUser(Long idUser) {
        if (!validation.existsUsuario(idUser)) return ResponseEntity.status(404).build();

        List<TopicoHasSeguidoresEntity> topicoEntityList = topicoHasSeguidoresRepositoy.findTopicosHasSeguidoresByIdUsuario(idUser);

        return topicoEntityList.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(topicoEntityList);
    }

    @Transactional
    public ResponseEntity<TopicoEntity> postTopicos(@RequestBody TopicoEntity topicoEntity) {
        topicoRepository.save(topicoEntity);
        return ResponseEntity.status(201).body(topicoEntity);
    }
}