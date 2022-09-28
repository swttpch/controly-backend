package controly.controller;

import controly.model.entity.TopicoEntity;
import controly.model.service.TopicoService;
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
    private TopicoService topicoService;

    List<TopicoEntity> topicoEntityList = new ArrayList<>();

    TopicoEntity topico = new TopicoEntity("Igor");

    @PostMapping("/{nome}")
    public ResponseEntity<TopicoEntity> addTopico(@PathVariable String nome){
        return topicoService.cadastrarTopico(nome);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoEntity> getTopico(@PathVariable long id){
        TopicoEntity topico = topicoService.buscarTopicoPeloId(id).get();
        return ResponseEntity.status(200).body(topico);
    }

    @GetMapping
    public ResponseEntity<List<TopicoEntity>> getTopicos(){
        topicoEntityList.add(topico);
        return ResponseEntity.status(200).body(topicoEntityList);
    }
}
