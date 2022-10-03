package controly.model.service;

import controly.model.entity.TopicoEntity;
import controly.model.entity.UsuarioEntity;
import controly.repository.TopicoRepository;
import controly.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import controly.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    UsuarioService usuarioService;


    public ResponseEntity<List<TopicoEntity>> getTopicos() {
        List<TopicoEntity> lista = topicoRepository.findAll();

        return lista.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(lista);
    }

    @Transactional
    public ResponseEntity<TopicoEntity> getTopicoById(Long id) {
        Optional<TopicoEntity> promisseTopico = topicoRepository.findByIdTopico(id);
        if (promisseTopico.isPresent()) {
            return ResponseEntity.status(200).body(promisseTopico.get());
        }
        return ResponseEntity.status(404).build();
    }
    public ResponseEntity<TopicoEntity> postTopicos(@RequestBody TopicoEntity topicoEntity) {
        topicoRepository.save(topicoEntity);
        List<TopicoEntity> lista = topicoRepository.findAll();
        return ResponseEntity.status(201).body(topicoEntity);
    }

    @Transactional
    public ResponseEntity<TopicoEntity> cadastrarTopico(TopicoEntity topico){
        topicoRepository.save(topico);
        return ResponseEntity.status(HttpStatus.CREATED).body(topico);
    public ResponseEntity<Map<Object, Object>> getTopicosMostFollowed() {

        Map<Object, Object> mapTopicos = new HashMap<>();

        return ResponseEntity.status(201).body(mapTopicos);
    }
}
