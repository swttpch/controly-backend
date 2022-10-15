package controly.model.service;

import controly.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ValidationService {
    @Autowired
    final private PostagemRepository postagemRepository;
    @Autowired
    final private ComentarioRepository comentarioRepository;
    @Autowired
    final private UsuarioRepository usuarioRepository;
    @Autowired
    final private TopicoRepository topicoRepository;
    @Autowired
    final private PontuacaoPostagemRepository pontuacaoRepository;

    public ValidationService(PostagemRepository postagemRepository, ComentarioRepository comentarioRepository, UsuarioRepository usuarioRepository, TopicoRepository topicoRepository, PontuacaoPostagemRepository pontuacaoRepository) {
        this.postagemRepository = postagemRepository;
        this.comentarioRepository = comentarioRepository;
        this.usuarioRepository = usuarioRepository;
        this.topicoRepository = topicoRepository;
        this.pontuacaoRepository = pontuacaoRepository;
    }

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
