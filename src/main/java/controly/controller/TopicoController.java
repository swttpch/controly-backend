package controly.controller;

import controly.model.entity.ComentarioEntity;
import controly.model.entity.TopicoEntity;
import controly.model.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoService topicoService;

    @PostMapping("")
    public ResponseEntity<TopicoEntity> addTopico(@RequestBody TopicoEntity topico){
        return topicoService.cadastrarTopico(topico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoEntity> getTopico(@PathVariable long id){
        return topicoService.getTopicoById(id);
    }

}
