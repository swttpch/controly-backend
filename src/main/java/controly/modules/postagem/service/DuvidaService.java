package controly.modules.postagem.service;

import controly.modules.perfilAndUsuario.entities.UsuarioEntity;
import controly.service.ValidationService;
import controly.modules.postagem.entities.Postagem;
import controly.modules.comentario.repository.ComentarioRepository;
import controly.modules.postagem.repository.PostagemRepository;
import controly.modules.topico.repository.TopicoRepository;
import controly.modules.perfilAndUsuario.repository.UsuarioRepository;
import controly.strategy.Ipostagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import static controly.config.Constant.IDNOTFOUND;

import javax.transaction.Transactional;

@Service
public class DuvidaService implements Ipostagem {
    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private ValidationService validation;

    public DuvidaService() {
    }


    @Override
    public ResponseEntity<String> enviarPostagem(Postagem duvida) {
        UsuarioEntity usuarioEntity = usuarioRepository.findByIdUsuario(duvida.getIdUsuario()).orElseThrow();
        if (validation.existsTopico(duvida.getIdTopico()) || validation.existsUsuario(duvida.getIdUsuario()))
            return ResponseEntity.status(404).body(IDNOTFOUND);
        postagemRepository.save(
                duvida.converterPostagem(
                        topicoRepository.findByIdTopico(duvida.getIdTopico()),
                        usuarioEntity
                ).initResposta()
        );
        return ResponseEntity.status(201).body("Duvida postada.");
    }

    @Transactional
    public ResponseEntity<String> definirRespostaDaPostagem(Long idPostagem, Long idComentario){
        if (validation.existsPostagem(idPostagem) || validation.existsComentario(idComentario))
            return ResponseEntity.status(404).body(IDNOTFOUND);
        postagemRepository.findByIdPostagem(idPostagem)
            .setResposta(comentarioRepository.findByIdComentario(idComentario));
        return ResponseEntity.status(201).body("Resposta atribuida.");
    }
}
