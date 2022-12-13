package controly.service;

import controly.entities.UsuarioEntity;
import controly.dto.TopicoDTO;
import controly.entities.TopicoEntity;
import controly.entities.TopicoHasSeguidoresEntity;
import controly.repository.TopicoHasSeguidoresRepositoy;
import controly.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static controly.config.Constant.IDNOTFOUND;

@SuppressWarnings("ALL")
@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private TopicoHasSeguidoresRepositoy topicoHasSeguidoresRepositoy;

    @Autowired
    private ValidationService validation;

    @Autowired
    private UsuarioService usuarioService;

    public TopicoService() {
    }

    public ResponseEntity<List<TopicoDTO>> getTopicos() {
        List<TopicoEntity> topicos = topicoRepository.findAll();

        if (topicos.isEmpty()) return ResponseEntity.status(204).build();

        List<TopicoDTO> topicoDTOList = new ArrayList<>();


        for (TopicoEntity topico : topicos) {

            TopicoDTO topicoDT = new TopicoDTO();

            topicoDT.setIdTopico(topico.getIdTopico());
            topicoDT.setNome(topico.getNome());
            topicoDT.setDescricao(topico.getDescricao());
            topicoDT.setSeguidores(topicoHasSeguidoresRepositoy.countTopicoHasSeguidoresByIdUsuario(topico.getIdTopico()));

            topicoDTOList.add(topicoDT);

        }

        return topicoDTOList.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(topicoDTOList);
    }

    @Transactional
    public ResponseEntity<TopicoDTO> getTopicoById(Long id) {
        if (validation.existsTopico(id)){
            return ResponseEntity.status(404).build();
        }
        TopicoEntity topico = topicoRepository.findByIdTopico(id);
        TopicoDTO topicoDTO = new TopicoDTO();

        topicoDTO.setIdTopico(topico.getIdTopico());
        topicoDTO.setNome(topico.getNome());
        topicoDTO.setDescricao(topico.getDescricao());
        topicoDTO.setSeguidores(topicoHasSeguidoresRepositoy.countTopicoHasSeguidoresByIdUsuario(topico.getIdTopico()));

        return ResponseEntity.status(200).body(topicoDTO);
    }

    public ResponseEntity<List<TopicoHasSeguidoresEntity>> getTopicosByIdUser(Long idUser) {
        if (validation.existsUsuario(idUser)) return ResponseEntity.status(404).build();

        List<TopicoHasSeguidoresEntity> topicoEntityList = topicoHasSeguidoresRepositoy.findTopicosHasSeguidoresTopicoEntityByUsuario_IdUsuario(idUser);

        return topicoEntityList.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(topicoEntityList);
    }

    @Transactional
    public ResponseEntity<TopicoEntity> postTopicos(@RequestBody TopicoEntity topicoEntity) {
        topicoRepository.save(topicoEntity);
        return ResponseEntity.status(201).body(topicoEntity);
    }

    @Transactional
    public ResponseEntity<?> followTopico(Long idTopico, Long idUsuario) {
        if (validation.existsTopico(idTopico) || validation.existsUsuario(idUsuario))
            return ResponseEntity.status(404).body(IDNOTFOUND);

        TopicoHasSeguidoresEntity topicoHasSeguidores = topicoHasSeguidoresRepositoy.findTopicoHasSeguidoresEntityByTopico_idTopicoAndUsuario_idUsuario(idTopico, idUsuario);

        if (topicoHasSeguidores == null ) {
            TopicoEntity topico = topicoRepository.findByIdTopico(idTopico);
            UsuarioEntity usuario = usuarioService.buscarUsuarioPorId(idUsuario).get();

            topicoHasSeguidores = new TopicoHasSeguidoresEntity();
            topicoHasSeguidores.setTopico(topico);
            topicoHasSeguidores.setUsuario(usuario);

            topicoHasSeguidoresRepositoy.save(topicoHasSeguidores);

            return ResponseEntity.status(201).body(String.format("Usuario de id %d comecou a seguir topico de id %d.", idTopico, idUsuario));

        } else {

            return ResponseEntity.status(404).body(String.format("Esse usuario já segue esse topico"));

        }


    }

    @Transactional
    public ResponseEntity<?> unfollowTopico(Long idTopico, Long idUsuario) {
        if (validation.existsTopico(idTopico) || validation.existsUsuario(idUsuario))
            return ResponseEntity.status(404).body(IDNOTFOUND);

        TopicoHasSeguidoresEntity topicoHasSeguidores = topicoHasSeguidoresRepositoy.findTopicoHasSeguidoresEntityByTopico_idTopicoAndUsuario_idUsuario(idTopico, idUsuario);

        if (topicoHasSeguidores == null ) {
            return ResponseEntity.status(404).body(String.format("Esse usuario não segue esse topico"));
        } else {

            topicoHasSeguidoresRepositoy.deleteById(topicoHasSeguidores.getId());

            return ResponseEntity.status(201).body(String.format("Usuario de id %d parou de seguir topico de id %d.", idTopico, idUsuario));

        }
    }

    public ResponseEntity<Boolean> userFollowTopico(Long idTopico, Long idUsuario) {
        Optional<TopicoHasSeguidoresEntity> topicoHasSeguidores = topicoHasSeguidoresRepositoy.existsFollowUser(idTopico, idUsuario);
        if (topicoHasSeguidores.isPresent()) return ResponseEntity.status(200).body(true);
        return ResponseEntity.status(200).body(false);
    }
}