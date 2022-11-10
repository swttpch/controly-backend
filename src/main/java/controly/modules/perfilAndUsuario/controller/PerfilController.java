package controly.modules.perfilAndUsuario.controller;

import controly.modules.perfilAndUsuario.dto.PerfilDTO;
import controly.modules.perfilAndUsuario.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

        @Autowired
        private PerfilService perfilService;

        @PreAuthorize("hasAnyRole('ADM')")
        @GetMapping("/{id}")
        public ResponseEntity<PerfilDTO> getPerfilById(@PathVariable long id){
            return perfilService.getPerfilById(id);
        }
}
