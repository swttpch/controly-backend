package controly.model.service;

import controly.controller.form.Comentario;
import controly.controller.form.Postagem;
import controly.model.entity.*;
import controly.repository.ComentarioRepository;
import controly.repository.PostagemRepository;
import controly.repository.TopicoRepository;
import controly.repository.UsuarioRepository;
import controly.strategy.Ipostagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class DuvidaService implements Ipostagem {
    @Autowired
    PostagemRepository postagemRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TopicoRepository topicoRepository;

    @Autowired
    ComentarioRepository comentarioRepository;

    @Autowired
    ValidationService validation;


    @Override
    public ResponseEntity enviarPostagem(Postagem duvida) {
        if (!validation.existsTopico(duvida.getIdTopico()) || !validation.existsUsuario(duvida.getIdUsuario()))
            return ResponseEntity.status(404).build();
        postagemRepository.save(
                duvida.converterPostagem(
                        topicoRepository.findByIdTopico(duvida.getIdTopico()),
                        usuarioRepository.findByIdUsuario(duvida.getIdUsuario())
                ).initResposta()
        );
        return ResponseEntity.status(201).build();
    }
    @Transactional
    public ResponseEntity definirRespostaDaPostagem(Long idPostagem, Long idComentario){
        if (!validation.existsPostagem(idPostagem) || !validation.existsComentario(idComentario))
            return ResponseEntity.status(404).build();

        postagemRepository.save(
                postagemRepository.findByIdPostagem(idPostagem)
                .setResposta(comentarioRepository.findByIdComentario(idComentario))
        );
        return ResponseEntity.status(200).build();
    }
}
