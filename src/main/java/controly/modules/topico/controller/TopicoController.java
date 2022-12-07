package controly.modules.topico.controller;

import controly.modules.topico.dto.TopicoDTO;
import controly.modules.topico.entities.TopicoEntity;
import controly.modules.topico.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    public TopicoController() {
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDTO> getTopico(@PathVariable long id){
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

    @GetMapping("/{idTopico}/{idUsuario}")
    public ResponseEntity<Boolean> userFollowTopico(@PathVariable Long idTopico, @PathVariable Long idUsuario) {
        return topicoService.userFollowTopico(idTopico, idUsuario);
    }
}
