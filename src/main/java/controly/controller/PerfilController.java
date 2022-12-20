package controly.controller;

import controly.dto.UserProfileResponse;
import controly.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    public PerfilController() {
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserProfileResponse> getPerfilById(@PathVariable long id) {
        return perfilService.getPerfilById(id);
    }
}
