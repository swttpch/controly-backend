package controly.controller;

import controly.model.entity.TopicoEntity;
import controly.model.entity.UsuarioEntity;
import controly.model.service.TopicoService;
import controly.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    TopicoRepository topicoRepository;

    @Autowired
    TopicoService topicoService;

    @GetMapping
    public ResponseEntity<List<TopicoEntity>> getTopicos(){
        return topicoService.getTopicos();
    }

    @PostMapping
    public ResponseEntity<TopicoEntity> postTopicos(@RequestBody TopicoEntity topicoEntity) {
        return topicoService.postTopicos(topicoEntity);
    }

    @GetMapping("/most/followed")
    public ResponseEntity<Map<Object, Object>> getTopicosMostFollowed() {
        return topicoService.getTopicosMostFollowed();
    }

}
