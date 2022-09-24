package controly.controller;

import controly.model.entity.TopicoEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {


    @GetMapping
    public ResponseEntity<List<TopicoEntity>> getTopicos(){
        return null;
    }

}
