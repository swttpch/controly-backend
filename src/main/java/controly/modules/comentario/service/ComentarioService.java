package controly.modules.comentario.service;

import controly.modules.perfilAndUsuario.entities.UsuarioEntity;
import controly.service.ValidationService;
import controly.modules.postagem.entities.Postagem;
import controly.modules.comentario.entities.ComentarioEntity;
import controly.modules.pontuacao.entities.pontuacaoComentario.PontuacaoComentario;
import controly.modules.comentario.repository.ComentarioRepository;
import controly.modules.postagem.repository.PontuacaoComentarioRepository;
import controly.modules.postagem.repository.PostagemRepository;
import controly.modules.perfilAndUsuario.repository.UsuarioRepository;
import controly.strategy.Ipostagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

import static controly.config.Constant.IDNOTFOUND;

@Service
public class ComentarioService implements Ipostagem {
    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;
    @Autowired
    private ValidationService validation;
    @Autowired
    private PontuacaoComentarioRepository pontuacaoComentarioRepository;

    public ComentarioService() {
    }

    @Override
    public ResponseEntity<String> enviarPostagem(Postagem post) {
        UsuarioEntity usuarioEntity = usuarioRepository.findByIdUsuario(post.getIdUsuario()).orElseThrow();
        if (validation.existsUsuario(post.getIdUsuario()) || validation.existsPostagem(post.getIdPostagem()))
            return ResponseEntity.status(404).body(IDNOTFOUND);
        comentarioRepository.save(
                post.converterPostagem(
                        postagemRepository.findByIdPostagem(post.getIdPostagem()),
                        usuarioEntity
                )
        );
        return ResponseEntity.status(201).body("Comentario postado.");
    }

    public ResponseEntity<String> excluirPostagem(Long idComentario) {
        if (validation.existsComentario(idComentario))
            return ResponseEntity.status(404).body(IDNOTFOUND);

        comentarioRepository.delete(
                comentarioRepository.findByIdComentario(idComentario)
        );
        return ResponseEntity.status(200).body("Comentário excluido.");
    }
    @Transactional
    public ResponseEntity<String> setPontuacaoComentario(Long comentario, Long usuario, int ponto){
        UsuarioEntity usuarioEntity = usuarioRepository.findByIdUsuario(usuario).orElseThrow();
        if (
                validation.existsComentario(comentario) ||
                        validation.existsUsuario(usuario)
        ) return ResponseEntity.status(404).body(IDNOTFOUND);

        if (!validation.existsPontuacaoWithComentarioAndUsuario(comentario, usuario)){
            pontuacaoComentarioRepository.save(
                    new PontuacaoComentario()
                            .setComentario(comentarioRepository.findByIdComentario(comentario))
                            .setUsuario(usuarioEntity)
                            .setPontuacao(ponto)
            );
        } else {
            pontuacaoComentarioRepository.setPontuacaoFor(comentario, usuario, ponto);
        }
        return ResponseEntity.status(200).body(String.format("Pontuação %d atribuida a comentário de ID %d.", ponto, comentario));
    }

    public ResponseEntity<List<ComentarioEntity>> getAllCommentsFromPost(Long idPostagem) {
        if (
                validation.existsPostagem(idPostagem)
        ) return ResponseEntity.status(404).build();
        List<ComentarioEntity> comentarios = comentarioRepository.findByPostagemIdPostagem(idPostagem);
        if (comentarios.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(comentarios);
    }
}
