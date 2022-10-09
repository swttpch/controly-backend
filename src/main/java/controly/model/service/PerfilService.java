package controly.model.service;

import controly.controller.dto.PerfilDTO;
import controly.model.entity.PostagemEntity;
import controly.model.entity.TopicoEntity;
import controly.model.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilService {
    @Autowired
    UsuarioService usuarioService;

    @Autowired
    TopicoService topicoService;

    @Autowired
    PostagemService postagemService;

    public ResponseEntity<PerfilDTO> getPerfilById(Long id){
        UsuarioEntity usuario = usuarioService.buscarUsuarioPorId(id).get();

        List<PostagemEntity> postagemEntityList = postagemService.getPostagemByIdUser(id).getBody();

        List<TopicoEntity> topicoEntityList = topicoService.getTopicosByIdUser(id).getBody();

        PerfilDTO perfilDTO = new PerfilDTO();

        perfilDTO.setUsuario(usuario);
        perfilDTO.setPostagens(postagemEntityList);
        perfilDTO.setTopicos_seguidos(topicoEntityList);

        return ResponseEntity.status(200).body(perfilDTO);
    }
}
