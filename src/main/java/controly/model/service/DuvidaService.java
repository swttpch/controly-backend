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


    @Override
    public ResponseEntity enviarPostagem(Postagem duvida) {
        TopicoEntity topico = topicoRepository.findById(duvida.getIdTopico()).get();
        UsuarioEntity usuario = usuarioRepository.findById(duvida.getIdUsuario()).get();
        PostagemEntity postagemEntity = duvida.converterPostagem(topico, usuario);
        postagemEntity.setRespostaDuvidaEntity(new RespostaDuvidaEntity().setResolvido(false));
        postagemRepository.save(postagemEntity);
        return ResponseEntity.status(201).body(postagemEntity);
    }
    @Transactional
    public ResponseEntity definirRespostaDaPostagem(Long idPostagem, Long idComentario){
        ComentarioEntity resposta = comentarioRepository.findById(idComentario).get();
        PostagemEntity duvida = postagemRepository.findById(idPostagem).get();
        duvida.getRespostaDuvidaEntity().setResposta(resposta)
                .setResolvido(true)
                .setResolvidoEm(LocalDateTime.now());
        postagemRepository.save(duvida);
        return ResponseEntity.status(200).build();
    }
}
