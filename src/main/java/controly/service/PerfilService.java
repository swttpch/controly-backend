package controly.service;

import controly.dto.PerfilDTO;
import controly.entity.UserEntity;
import controly.entity.TopicEntity;
import controly.repository.TopicHasFollowersRepository;
import controly.entity.TopicHasFollowersEntity;
import controly.entity.PostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
@Service
public class PerfilService {
    @Autowired
    private UserService userService;

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private TopicHasFollowersRepository topicHasFollowersRepository;

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
        UserEntity usuario = userService.getUserById(id);

        List<PostEntity> postEntityList = postagemService.getPostagemByIdUser(id).getBody();

        List<TopicHasFollowersEntity> topicoHasSeguidoresEntities =
                topicHasFollowersRepository.findByFollowerIdUser(id);
        List<TopicEntity> topicosSeguidos = topicoHasSeguidoresEntities
                .stream()
                .map(topico -> topico.getTopic())
                .collect(Collectors.toList());

        PerfilDTO perfilDTO = new PerfilDTO();

        perfilDTO.setUsuario(usuario);
        perfilDTO.setPostagens(postEntityList);
        perfilDTO.setTopicos_seguidos(topicosSeguidos);
        if (perfilDTO.getPostagens() == null) return ResponseEntity.status(200).body(perfilDTO);
        if (perfilDTO.getPostagens().size() == 0) return ResponseEntity.status(200).body(perfilDTO);
        PostEntity postagemMaiorPontuacao = postEntityList.stream().max(Comparator.comparing(PostEntity::getPoints)).orElse(null);
        perfilDTO.setPostagemMaiorPontuacao(postagemMaiorPontuacao);
        return ResponseEntity.status(200).body(perfilDTO);
    }
}
