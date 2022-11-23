package controly.modules.perfilAndUsuario.service;

import controly.modules.perfilAndUsuario.dto.PerfilDTO;
import controly.modules.perfilAndUsuario.entities.UsuarioEntity;
import controly.modules.topico.entities.TopicoEntity;
import controly.modules.topico.repository.TopicoHasSeguidoresRepositoy;
import controly.modules.topico.service.TopicoService;
import controly.service.ValidationService;
import controly.modules.topico.entities.TopicoHasSeguidoresEntity;
import controly.modules.postagem.entities.PostagemEntity;
import controly.modules.postagem.service.PostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
@Service
public class PerfilService {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private TopicoHasSeguidoresRepositoy topicoHasSeguidoresRepositoy;

    @Autowired
    private ValidationService validation;

    @Autowired
    private PostagemService postagemService;

    public PerfilService() {
    }

    public ResponseEntity<PerfilDTO> getPerfilById(Long id){
        if (validation.existsUsuario(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuário não encontrado");
        }
        UsuarioEntity usuario = usuarioService.buscarUsuarioPorId(id).get();

        List<PostagemEntity> postagemEntityList = postagemService.getPostagemByIdUser(id).getBody();

        List<TopicoHasSeguidoresEntity> topicoHasSeguidoresEntities =
                topicoHasSeguidoresRepositoy.findTopicosHasSeguidoresTopicoEntityByUsuario_IdUsuario(id);
        List<TopicoEntity> topicosSeguidos = topicoHasSeguidoresEntities
                .stream()
                .map(topico -> topico.getTopico())
                .collect(Collectors.toList());

        PerfilDTO perfilDTO = new PerfilDTO();

        perfilDTO.setUsuario(usuario);
        perfilDTO.setPostagens(postagemEntityList);
        perfilDTO.setTopicos_seguidos(topicosSeguidos);
        if (perfilDTO.getPostagens() == null) return ResponseEntity.status(200).body(perfilDTO);
        if (perfilDTO.getPostagens().size() == 0) return ResponseEntity.status(200).body(perfilDTO);
        PostagemEntity postagemMaiorPontuacao = postagemEntityList.stream().max(Comparator.comparing(PostagemEntity::getPontuacao)).orElse(null);
        perfilDTO.setPostagemMaiorPontuacao(postagemMaiorPontuacao);
        return ResponseEntity.status(200).body(perfilDTO);
    }
}
