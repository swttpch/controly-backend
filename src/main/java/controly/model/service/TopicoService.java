package controly.model.service;

import controly.model.entity.TopicoEntity;
import controly.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {
    List<TopicoEntity> topicoEntityList = new ArrayList<>();
    @Autowired
    private TopicoRepository topicoRepository;
    public ResponseEntity<List<TopicoEntity>> getTopicos(){

        if(true){
            return ResponseEntity.status(200).body(topicoEntityList);
        } else {
            return ResponseEntity.status(404).body(null);
        }

    }

    @Transactional
    public Optional<TopicoEntity> buscarTopicoPeloId(Long id) {
        Optional<TopicoEntity> promisseTopico = topicoRepository.findByIdTopico(id);
        System.out.println(promisseTopico);
        System.out.println(promisseTopico.isPresent());
        return promisseTopico;
    }


    @Transactional
    public ResponseEntity<TopicoEntity> cadastrarTopico(String nome){
        TopicoEntity topico = new TopicoEntity(nome);
        System.out.println(topico.getNome());
        topicoRepository.save(topico);
        System.out.println("cadastrado");
        return ResponseEntity.status(HttpStatus.CREATED).body(topico);
    }
}
