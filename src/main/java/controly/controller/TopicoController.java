package controly.controller;

import controly.model.entity.TopicoEntity;
import controly.model.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @GetMapping("/{id}")
    public ResponseEntity<TopicoEntity> getTopico(@PathVariable long id){
        return topicoService.getTopicoById(id);
    }

    @GetMapping
    public ResponseEntity<List<TopicoEntity>> getTopicos(){
        return topicoService.getTopicos();
    }

    @PostMapping
    public ResponseEntity<TopicoEntity> postTopicos(@RequestBody TopicoEntity topicoEntity) {
        return topicoService.postTopicos(topicoEntity);
    }

}
