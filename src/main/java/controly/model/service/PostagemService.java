package controly.model.service;

import controly.model.entity.PostagemEntity;
import controly.model.entity.TopicoEntity;
import controly.model.entity.UsuarioEntity;
import controly.repository.PostagemRepository;
import controly.repository.TopicoRepository;
import controly.repository.UsuarioRepository;
import controly.controller.form.Discussao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PostagemService {

    @Autowired
    PostagemRepository postagemRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TopicoRepository topicoRepository;

    @Transactional
    public ResponseEntity cadastrarDiscussao(Discussao novaPostagem){
        System.out.println(novaPostagem.getIdTopico());
        System.out.println(novaPostagem.getIdUsuario());
        TopicoEntity topico = topicoRepository.findById(novaPostagem.getIdTopico()).get();
        UsuarioEntity usuario = usuarioRepository.findById(novaPostagem.getIdUsuario()).get();
        PostagemEntity postagemEntity = novaPostagem.converterPostagem(topico, usuario);
        postagemRepository.save(postagemEntity);
        return ResponseEntity.status(201).body(postagemEntity);
    }

    @Transactional
    public List<PostagemEntity> buscarTodasPostagens(){
        return postagemRepository.findAll();
    }
}
