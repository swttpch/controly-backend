package controly.modules.postagem.service;

import controly.modules.comentario.entities.ComentarioEntity;
import controly.modules.comentario.repository.ComentarioRepository;
import controly.modules.pontuacao.entities.pontuacaoComentario.PontuacaoComentario;
import controly.modules.pontuacao.entities.pontuacaoPostagem.PontuacaoPostagem;
import controly.modules.postagem.entities.PostagemEntity;
import controly.modules.postagem.repository.PontuacaoComentarioRepository;
import controly.service.ValidationService;
import controly.modules.postagem.repository.PostagemRepository;
import controly.modules.postagem.repository.PontuacaoPostagemRepository;
import controly.modules.perfilAndUsuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static controly.config.Constant.IDNOTFOUND;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.Delayed;

@Service
public class PostagemService {
    @Autowired
    final private ValidationService validation;
    @Autowired
    final private PostagemRepository postagemRepository;
    @Autowired
    final private PontuacaoPostagemRepository pontuacaoPostagemRepository;

    @Autowired
    final private PontuacaoComentarioRepository pontuacaoComentarioRepository;

    @Autowired
    final private UsuarioRepository usuarioRepository;

    @Autowired
    final private ComentarioRepository comentarioRepository;

    public PostagemService(ValidationService validation, PostagemRepository postagemRepository, PontuacaoPostagemRepository pontuacaoPostagemRepository, PontuacaoComentarioRepository pontuacaoComentarioRepository, UsuarioRepository usuarioRepository, ComentarioRepository comentarioRepository) {
        this.validation = validation;
        this.postagemRepository = postagemRepository;
        this.pontuacaoPostagemRepository = pontuacaoPostagemRepository;
        this.pontuacaoComentarioRepository = pontuacaoComentarioRepository;
        this.usuarioRepository = usuarioRepository;
        this.comentarioRepository = comentarioRepository;
    }

    @Transactional
    public ResponseEntity<List<PostagemEntity>> todasPostagens() {
        List<PostagemEntity> postagemEntities = postagemRepository.findAll();
        if (postagemEntities.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(postagemEntities);
    }

    @Transactional
    public ResponseEntity<PostagemEntity> pegarPostagemPeloId(Long id) {
        if (validation.existsPostagem(id)) return ResponseEntity.status(404).build();
        PostagemEntity postagem = postagemRepository.findByIdPostagem(id);
        return ResponseEntity.status(200).body(postagem);
    }

    public ResponseEntity<String> setPontuacaoPostagem(Long postagem, Long usuario, int ponto) {
        if (validation.existsPostagem(postagem) || validation.existsUsuario(usuario))
            return ResponseEntity.status(404).body(IDNOTFOUND);

        if (!validation.existsPontuacaoWithPostagemAndUsuario(postagem, usuario)) {
            pontuacaoPostagemRepository.save(new PontuacaoPostagem().setPostagem(postagemRepository.findByIdPostagem(postagem)).setUsuario(usuarioRepository.findByIdUsuario(usuario)).setPontuacao(ponto));
        } else {
            pontuacaoPostagemRepository.setPontuacaoFor(postagem, usuario, ponto);
        }
        return ResponseEntity.status(200).body(String.format("Pontuação %d atribuida a postagem de ID %d.", ponto, postagem));
    }
    @Transactional
    public ResponseEntity<String> excluirPostagem(Long idPostagem) {
        if (validation.existsPostagem(idPostagem)) return ResponseEntity.status(404).body(IDNOTFOUND);

        PostagemEntity postagem = postagemRepository.findByIdPostagem(idPostagem);

        for (ComentarioEntity comentario : postagem.getComentarios()) {
            for (PontuacaoComentario pontuacaoComentario : comentario.getPontuacaoComentarios()) {
                pontuacaoComentarioRepository.deleteByComentario_idComentario(pontuacaoComentario.getComentario().getIdComentario());
            }

            if (comentario.getPontuacaoComentarios().isEmpty()) {
                comentarioRepository.deleteById(comentario.getIdComentario());
            }
        }

        pontuacaoPostagemRepository.deleteByPostagem_idPostagem(idPostagem);

        postagemRepository.delete(postagem);

        return ResponseEntity.status(200).body("Postagem de ID " + idPostagem + " excluida.");
    }

    @Transactional
    public ResponseEntity<List<PostagemEntity>> getPostagemByIdUser(Long idUser) {
        if (validation.existsUsuario(idUser)) return ResponseEntity.status(404).build();

        List<PostagemEntity> postagemEntityList = postagemRepository.findByDonoIdUsuario(idUser);

        return postagemEntityList.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(postagemEntityList);
    }

    @Transactional
    public ResponseEntity<PontuacaoPostagem> findPontuacaoPostagem(Long postagem, Long usuario, int pontuacao) {
        PontuacaoPostagem pontuacaoPostagem = pontuacaoPostagemRepository.findByPostagemIdPostagemAndUsuarioIdUsuario(postagem, usuario).get();
        pontuacaoPostagem.setPontuacao(pontuacao);
        return ResponseEntity.status(200).body(pontuacaoPostagem);
    }
}
