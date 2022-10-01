package controly.controller;

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

    @PostMapping("/{nome}")
    public ResponseEntity<TopicoEntity> addTopico(@PathVariable String nome){
        return topicoService.cadastrarTopico(nome);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoEntity> getTopico(@PathVariable long id){
        return topicoService.getTopicoById(id);
    }

}
