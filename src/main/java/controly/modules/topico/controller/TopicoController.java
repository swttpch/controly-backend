package controly.modules.topico.controller;

import controly.modules.topico.TopicoDTO;
import controly.modules.topico.entities.TopicoEntity;
import controly.modules.topico.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    final private TopicoService topicoService;
    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<TopicoEntity> getTopico(@PathVariable long id){
        return topicoService.getTopicoById(id);
    }

    @GetMapping
    public ResponseEntity<List<TopicoDTO>> getTopicos(){
        return topicoService.getTopicos();
    }

    @PostMapping("/{idTopico}/{idUsuario}")
    public ResponseEntity<?> followTopico(@PathVariable Long idTopico, @PathVariable Long idUsuario) {
        return topicoService.followTopico(idTopico, idUsuario);
    }

    @DeleteMapping("/{idTopico}/{idUsuario}")
    public ResponseEntity<?> unfollowTopico(@PathVariable Long idTopico, @PathVariable Long idUsuario) {
        return topicoService.unfollowTopico(idTopico, idUsuario);
    }

    @PostMapping
    public ResponseEntity<TopicoEntity> postTopicos(@RequestBody TopicoEntity topicoEntity) {
        return topicoService.postTopicos(topicoEntity);
    }

}
