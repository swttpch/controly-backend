package controly.model.service;

import controly.model.entity.PostagemEntity;
import controly.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ValidationService {
    @Autowired
    PostagemRepository postagemRepository;
    @Autowired
    ComentarioRepository comentarioRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    TopicoRepository topicoRepository;
    @Autowired
    PontuacaoPostagemRepository pontuacaoRepository;
    @Transactional
    public boolean existsUsuario(Long idUsuario){
        return usuarioRepository.findById(idUsuario).isPresent();
    }
    @Transactional
    public boolean existsPostagem(Long idPostagem){
        return postagemRepository.findById(idPostagem).isPresent();
    }

    @Transactional
    public boolean existsComentario(Long idComentario){
        return comentarioRepository.findById(idComentario).isPresent();
    }
    @Transactional
    public boolean existsTopico(Long idTopico){
        return topicoRepository.findById(idTopico).isPresent();
    }

    @Transactional
    public boolean existsPontuacaoWithPostagemAndUsuario(Long idPostagem, Long idUsuario){
        return pontuacaoRepository.existByPostagemAndUsuario(idPostagem, idUsuario).isPresent();
    }
}
