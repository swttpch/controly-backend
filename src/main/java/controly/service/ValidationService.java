package controly.service;

import controly.modules.comentario.repository.ComentarioRepository;
import controly.modules.perfilAndUsuario.repository.UsuarioRepository;
import controly.modules.postagem.repository.PontuacaoComentarioRepository;
import controly.modules.postagem.repository.PontuacaoPostagemRepository;
import controly.modules.postagem.repository.PostagemRepository;
import controly.modules.topico.repository.TopicoRepository;
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

    @Transactional
    public boolean existsUsuario(Long idUsuario){
        return usuarioRepository.findById(idUsuario).isEmpty();
    }
    @Transactional
    public boolean existsPostagem(Long idPostagem){
        return postagemRepository.findById(idPostagem).isEmpty();
    }

    @Transactional
    public boolean existsComentario(Long idComentario){
        return comentarioRepository.findById(idComentario).isEmpty();
    }
    @Transactional
    public boolean existsTopico(Long idTopico){
        return topicoRepository.findById(idTopico).isEmpty();
    }

    @Transactional
    public boolean existsPontuacaoWithPostagemAndUsuario(Long idPostagem, Long idUsuario){
        return pontuacaoPostagemRepository.existByPostagemAndUsuario(idPostagem, idUsuario).isPresent();
    }
    @Transactional
    public boolean existsPontuacaoWithComentarioAndUsuario(Long idComentario, Long idUsuario){
        return pontuacaoComentarioRepository.existByComentarioAndUsuario(idComentario, idUsuario).isPresent();
    }

}
