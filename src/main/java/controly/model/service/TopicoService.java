package controly.model.service;

import controly.model.entity.PostagemEntity;
import controly.model.entity.TopicoEntity;
import controly.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import javax.transaction.Transactional;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private ValidationService validation;

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

//    @Transactional
//    public ResponseEntity<List<TopicoEntity>> getTopicosByIdUser(Long idUser) {
//        if (!validation.existsUsuario(idUser)) return ResponseEntity.status(404).build();
//
//        List<TopicoEntity> topicoEntityList = topicoRepository.findAll();
//
//        for (TopicoEntity topico : postagemEntityList) {
//            if (!topico.get().getIdUsuario().equals(idUser)) {
//                topicoEntityList.remove(postagem);
//            }
//        }
//        return ResponseEntity.status(200).body(postagemEntityList);
//    }

    @Transactional
    public ResponseEntity<TopicoEntity> postTopicos(@RequestBody TopicoEntity topicoEntity) {
        topicoRepository.save(topicoEntity);
        return ResponseEntity.status(201).body(topicoEntity);
    }
}