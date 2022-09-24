package controly.controller;

import controly.model.entity.TopicoEntity;
import controly.model.service.TopicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    TopicoService topicoService;

    List<TopicoEntity> topicoEntityList = new ArrayList<>();

    TopicoEntity topico = new TopicoEntity("Igor");

    @GetMapping
    public ResponseEntity<List<TopicoEntity>> getTopicos(){
        topicoEntityList.add(topico);
        return ResponseEntity.status(200).body(topicoEntityList);    }

}
