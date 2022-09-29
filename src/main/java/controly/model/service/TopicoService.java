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

    @Transactional
    public ResponseEntity<TopicoEntity> getTopicoById(Long id) {
        Optional<TopicoEntity> promisseTopico = topicoRepository.findByIdTopico(id);
        if (promisseTopico.isPresent()) {
            return ResponseEntity.status(200).body(promisseTopico.get());
        }
        return ResponseEntity.status(404).build();
    }

    @Transactional
    public ResponseEntity<TopicoEntity> cadastrarTopico(String nome){
        TopicoEntity topico = new TopicoEntity();
        topico.setNome(nome);
        topicoRepository.save(topico);
        return ResponseEntity.status(HttpStatus.CREATED).body(topico);
    }
}
