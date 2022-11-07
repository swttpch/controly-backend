package controly.model.service;

import controly.controller.dto.PerfilDTO;
import controly.model.entity.PostagemEntity;
import controly.model.entity.TopicoEntity;
import controly.model.entity.TopicoHasSeguidoresEntity;
import controly.model.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilService {
    @Autowired
    final private UsuarioService usuarioService;

    @Autowired
    final private TopicoService topicoService;

    @Autowired
    final private ValidationService validation;

    @Autowired
    final private PostagemService postagemService;

    public PerfilService(UsuarioService usuarioService, TopicoService topicoService, ValidationService validation, PostagemService postagemService) {
        this.usuarioService = usuarioService;
        this.topicoService = topicoService;
        this.validation = validation;
        this.postagemService = postagemService;
    }

    public ResponseEntity<PerfilDTO> getPerfilById(Long id){
        if (!validation.existsUsuario(id)) return ResponseEntity.status(404).build();
        UsuarioEntity usuario = usuarioService.buscarUsuarioPorId(id).get();

        List<PostagemEntity> postagemEntityList = postagemService.getPostagemByIdUser(id).getBody();

        List<TopicoHasSeguidoresEntity> topicoEntityList = topicoService.getTopicosByIdUser(id).getBody();

        PerfilDTO perfilDTO = new PerfilDTO();

        perfilDTO.setUsuario(usuario);
        perfilDTO.setPostagens(postagemEntityList);
        perfilDTO.setTopicos_seguidos(topicoEntityList);

        return ResponseEntity.status(200).body(perfilDTO);
    }
}
