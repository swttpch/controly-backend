package controly.model.service;

import controly.model.entity.TopicoEntity;
import controly.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private ValidationService validation;

    @Transactional
    public ResponseEntity<TopicoEntity> getTopicoById(Long id) {
        if (!validation.existsTopico(id)) return ResponseEntity.status(404).build();
        return ResponseEntity.status(200).body(topicoRepository.findByIdTopico(id));
    }

    @Transactional
    public ResponseEntity<TopicoEntity> cadastrarTopico(TopicoEntity topico){
        topicoRepository.save(topico);
        return ResponseEntity.status(HttpStatus.CREATED).body(topico);
    }
}
