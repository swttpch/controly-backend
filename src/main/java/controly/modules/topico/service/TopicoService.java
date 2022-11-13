package controly.modules.topico.service;

import controly.modules.perfilAndUsuario.entities.UsuarioEntity;
import controly.modules.perfilAndUsuario.service.UsuarioService;
import controly.modules.topico.TopicoDTO;
import controly.modules.topico.entities.TopicoEntity;
import controly.modules.topico.entities.TopicoHasSeguidoresEntity;
import controly.modules.topico.repository.TopicoHasSeguidoresRepositoy;
import controly.service.ValidationService;
import controly.modules.topico.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import javax.transaction.Transactional;

import static controly.config.Constant.IDNOTFOUND;

@SuppressWarnings("ALL")
@Service
public class TopicoService {
    @Autowired
    final private TopicoRepository topicoRepository;

    @Autowired
    private final TopicoHasSeguidoresRepositoy topicoHasSeguidoresRepositoy;

    @Autowired
    final private ValidationService validation;

    @Autowired
    final private UsuarioService usuarioService;

    public TopicoService(TopicoRepository topicoRepository, TopicoHasSeguidoresRepositoy topicoHasSeguidoresRepositoy, ValidationService validation, UsuarioService usuarioService) {
        this.topicoRepository = topicoRepository;
        this.topicoHasSeguidoresRepositoy = topicoHasSeguidoresRepositoy;
        this.validation = validation;
        this.usuarioService = usuarioService;
    }

    public ResponseEntity<List<TopicoDTO>> getTopicos() {
        List<TopicoEntity> topicos = topicoRepository.findAll();

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
    public ResponseEntity<TopicoEntity> getTopicoById(Long id) {
        if (validation.existsTopico(id)) return ResponseEntity.status(404).build();
        return ResponseEntity.status(200).body(topicoRepository.findByIdTopico(id));
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
        if (validation.existsTopico(idTopico) && validation.existsUsuario(idUsuario))
            return ResponseEntity.status(404).body(IDNOTFOUND);
        TopicoHasSeguidoresEntity topicoHasSeguidores = new TopicoHasSeguidoresEntity();
        TopicoEntity topico = topicoRepository.findByIdTopico(idTopico);
        UsuarioEntity usuario = usuarioService.buscarUsuarioPorId(idUsuario).get();

        topicoHasSeguidores.setTopico(topico);
        topicoHasSeguidores.setUsuario(usuario);

        topicoHasSeguidoresRepositoy.save(topicoHasSeguidores);

        return ResponseEntity.status(201).body(String.format("Usuario de id %d comecou a seguir topico de id %d.", idTopico, idUsuario));
    }

    @Transactional
    public ResponseEntity<?> unfollowTopico(Long idTopico, Long idUsuario) {
        if (validation.existsTopico(idTopico) && validation.existsUsuario(idUsuario))
            return ResponseEntity.status(404).body(IDNOTFOUND);

        TopicoHasSeguidoresEntity topicoHasSeguidores = new TopicoHasSeguidoresEntity();

        topicoHasSeguidores.setId(topicoHasSeguidoresRepositoy.findTopicoHasSeguidoresEntityByTopico_idTopicoAndUsuario_idUsuario(idTopico, idUsuario));

        topicoHasSeguidoresRepositoy.deleteById(topicoHasSeguidoresRepositoy.findTopicoHasSeguidoresEntityByTopico_idTopicoAndUsuario_idUsuario(idTopico, idUsuario));

        return ResponseEntity.status(201).body(String.format("Usuario de id %d parou de seguir topico de id %d.", idTopico, idUsuario));
    }
}