package controly.service;

import controly.repository.ComentarioRepository;
import controly.repository.UsuarioRepository;
import controly.repository.PontuacaoComentarioRepository;
import controly.repository.PontuacaoPostagemRepository;
import controly.repository.PostagemRepository;
import controly.repository.TopicoRepository;
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
    final private PontuacaoPostagemRepository pontuacaoPostagemRepository;

    @Autowired
    final private PontuacaoComentarioRepository pontuacaoComentarioRepository;

    public ValidationService(PostagemRepository postagemRepository, ComentarioRepository comentarioRepository, UsuarioRepository usuarioRepository, TopicoRepository topicoRepository, PontuacaoPostagemRepository pontuacaoPostagemRepository, PontuacaoComentarioRepository pontuacaoComentarioRepository) {
        this.postagemRepository = postagemRepository;
        this.comentarioRepository = comentarioRepository;
        this.usuarioRepository = usuarioRepository;
        this.topicoRepository = topicoRepository;
        this.pontuacaoPostagemRepository = pontuacaoPostagemRepository;
        this.pontuacaoComentarioRepository = pontuacaoComentarioRepository;
    }


    public boolean existsUsuario(Long idUsuario){
        return usuarioRepository.findById(idUsuario).isEmpty();
    }

    public boolean existsPostagem(Long idPostagem){
        return postagemRepository.findById(idPostagem).isEmpty();
    }


    public boolean existsComentario(Long idComentario){
        return comentarioRepository.findById(idComentario).isEmpty();
    }

    public boolean existsTopico(Long idTopico){
        return topicoRepository.findById(idTopico).isEmpty();
    }


    public boolean existsPontuacaoWithPostagemAndUsuario(Long idPostagem, Long idUsuario) {
        return pontuacaoPostagemRepository.existByPostagemAndUsuario(idPostagem, idUsuario).isPresent();
    }

}
