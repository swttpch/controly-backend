package controly.modules.perfilAndUsuario.controller;

import controly.modules.perfilAndUsuario.dto.PerfilDTO;
import controly.modules.perfilAndUsuario.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

        @Autowired
        private final PerfilService perfilService;

        public PerfilController(PerfilService perfilService) {
                this.perfilService = perfilService;
        }

        @GetMapping("/{id}")
        public ResponseEntity<PerfilDTO> getPerfilById(@PathVariable long id){
            return perfilService.getPerfilById(id);
        }
}
