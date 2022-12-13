package controly.service;

import controly.entities.UsuarioEntity;
import controly.strategy.Postagem;
import controly.entities.ComentarioEntity;
import controly.entities.PontuacaoComentario;
import controly.repository.ComentarioRepository;
import controly.repository.PontuacaoComentarioRepository;
import controly.repository.PostagemRepository;
import controly.repository.UsuarioRepository;
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
                post.converterComentario(
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
    public ResponseEntity<String> setPontuacaoComentario(Long comentario, Long usuario){
        if (
                validation.existsComentario(comentario) ||
                        validation.existsUsuario(usuario)
        ) return ResponseEntity.status(404).body(IDNOTFOUND);
        ComentarioEntity comentario1 = comentarioRepository.findByIdComentario(comentario);
        UsuarioEntity usuarioEntity = usuarioRepository.findByIdUsuario(usuario).orElseThrow();
        if (!pontuacaoComentarioRepository.existByComentarioAndUsuario(comentario, usuario).isPresent()){
            pontuacaoComentarioRepository.save(
                    new PontuacaoComentario()
                            .setComentario(comentario1)
                            .setUsuario(usuarioEntity)
            );
            return ResponseEntity.status(200).body("Curtida criada");
        } else {
            pontuacaoComentarioRepository.deleteByComentario_idComentario(comentario,usuario);
            return ResponseEntity.status(200).body("Curtida excluida");
        }
    }

    public ResponseEntity<Boolean> existsCurtida(Long comentario, Long usuario){
        if (
                validation.existsComentario(comentario) ||
                        validation.existsUsuario(usuario)
        ) return ResponseEntity.status(404).build();
        if (!pontuacaoComentarioRepository.existByComentarioAndUsuario(comentario, usuario).isPresent()){

            return ResponseEntity.status(200).body(false);
        } else {
            return ResponseEntity.status(200).body(true);
        }
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
