package controly.modules.perfilAndUsuario.service;

import controly.modules.perfilAndUsuario.dto.PerfilDTO;
import controly.modules.perfilAndUsuario.entities.UsuarioEntity;
import controly.service.ValidationService;
import controly.postagem.entities.PostagemEntity;
import controly.postagem.service.PostagemService;
import controly.model.entity.TopicoHasSeguidoresEntity;
import controly.perfilAndUsuario.entities.UsuarioEntity;
import controly.topico.service.TopicoService;
import controly.modules.postagem.entities.PostagemEntity;
import controly.modules.postagem.service.PostagemService;
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

    public PerfilService(UsuarioService usuarioService, ValidationService validation, TopicoService topicoService, PostagemService postagemService) {
        this.usuarioService = usuarioService;
        this.validation = validation;
        this.topicoService = topicoService;
        this.postagemService = postagemService;
    }

    public ResponseEntity<PerfilDTO> getPerfilById(Long id){
        if (validation.existsUsuario(id)) return ResponseEntity.status(404).build();
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
