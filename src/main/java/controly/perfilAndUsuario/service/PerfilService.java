package controly.perfilAndUsuario.service;

import controly.perfilAndUsuario.dto.PerfilDTO;
import controly.service.ValidationService;
import controly.postagem.entities.PostagemEntity;
import controly.postagem.service.PostagemService;
import controly.perfilAndUsuario.entities.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilService {
    @Autowired
    final private UsuarioService usuarioService;

    @Autowired
    final private ValidationService validation;

    @Autowired
    final private PostagemService postagemService;

    public PerfilService(UsuarioService usuarioService, ValidationService validation, PostagemService postagemService) {
        this.usuarioService = usuarioService;
        this.validation = validation;
        this.postagemService = postagemService;
    }

    public ResponseEntity<PerfilDTO> getPerfilById(Long id){
        if (validation.existsUsuario(id)) return ResponseEntity.status(404).build();
        UsuarioEntity usuario = usuarioService.buscarUsuarioPorId(id).get();

        List<PostagemEntity> postagemEntityList = postagemService.getPostagemByIdUser(id).getBody();

        PerfilDTO perfilDTO = new PerfilDTO();

        perfilDTO.setUsuario(usuario);
        perfilDTO.setPostagens(postagemEntityList);

        return ResponseEntity.status(200).body(perfilDTO);
    }
}
