package controly.perfilAndUsuario.controller;

import controly.perfilAndUsuario.dto.PerfilDTO;
import controly.perfilAndUsuario.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

        @Autowired
        private PerfilService perfilService;

        @GetMapping("/{id}")
        public ResponseEntity<PerfilDTO> getPerfilById(@PathVariable long id){
            return perfilService.getPerfilById(id);
        }
}
