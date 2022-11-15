package controly.modules.perfilAndUsuario.service;

import controly.modules.perfilAndUsuario.dto.PerfilDTO;
import controly.modules.perfilAndUsuario.entities.UsuarioEntity;
import controly.service.ValidationService;
import controly.modules.postagem.entities.PostagemEntity;
import controly.modules.postagem.service.PostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        if (validation.existsUsuario(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuário não encontrado");
        }
        UsuarioEntity usuario = usuarioService.buscarUsuarioPorId(id).get();

        List<PostagemEntity> postagemEntityList = postagemService.getPostagemByIdUser(id).getBody();

        PerfilDTO perfilDTO = new PerfilDTO();

        perfilDTO.setUsuario(usuario);
        perfilDTO.setPostagens(postagemEntityList);

        return ResponseEntity.status(200).body(perfilDTO);
    }
}
