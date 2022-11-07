package controly.model.service;

import controly.model.entity.PontuacaoPostagem;
import controly.model.entity.PostagemEntity;
import controly.repository.PontuacaoPostagemRepository;
import controly.repository.PostagemRepository;
import controly.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import static controly.config.Constant.IDNOTFOUND;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PostagemService {
    @Autowired
    final private ValidationService validation;
    @Autowired
    final private PostagemRepository postagemRepository;
    @Autowired
    final private PontuacaoPostagemRepository pontuacaoPostagemRepository;

    @Autowired
    final private UsuarioRepository usuarioRepository;

    public PostagemService(ValidationService validation, PostagemRepository postagemRepository, PontuacaoPostagemRepository pontuacaoPostagemRepository, UsuarioRepository usuarioRepository) {
        this.validation = validation;
        this.postagemRepository = postagemRepository;
        this.pontuacaoPostagemRepository = pontuacaoPostagemRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public ResponseEntity<List<PostagemEntity>> todasPostagens() {
        List<PostagemEntity> postagemEntities = postagemRepository.findAll();
        if (postagemEntities.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(postagemEntities);
    }

    @Transactional
    public ResponseEntity<PostagemEntity> pegarPostagemPeloId(Long id){
        if (!validation.existsPostagem(id)) return ResponseEntity.status(404).build();
        PostagemEntity postagem = postagemRepository.findByIdPostagem(id);
        return ResponseEntity.status(200).body(postagem);
    }

    public ResponseEntity<String> setPontuacaoPostagem(Long postagem, Long usuario, int ponto){
        if (
            !validation.existsPostagem(postagem) ||
            !validation.existsUsuario(usuario)
        ) return ResponseEntity.status(404).body(IDNOTFOUND);

        if (!validation.existsPontuacaoWithPostagemAndUsuario(postagem, usuario)){
            pontuacaoPostagemRepository.save(
                    new PontuacaoPostagem()
                            .setPostagem(postagemRepository.findByIdPostagem(postagem))
                            .setUsuario(usuarioRepository.findByIdUsuario(usuario))
                            .setPontuacao(ponto)
            );
        } else {
            pontuacaoPostagemRepository.setPontuacaoFor(postagem, usuario, ponto);
        }
        return ResponseEntity.status(200).body(String.format("Pontuação %d atribuida a postagem de ID %d.", ponto, postagem));
    }

    public ResponseEntity<String> excluirPostagem(Long idPostagem) {
        if (!validation.existsPostagem(idPostagem))
            return ResponseEntity.status(404).body(IDNOTFOUND);

        postagemRepository.delete(
                postagemRepository.findByIdPostagem(idPostagem)
        );
        return ResponseEntity.status(200).body("Postagem de ID "+idPostagem+" excluida.");
    }

    @Transactional
    public ResponseEntity<List<PostagemEntity>> getPostagemByIdUser(Long idUser){
        if (!validation.existsUsuario(idUser)) return ResponseEntity.status(404).build();

        List<PostagemEntity> postagemEntityList = postagemRepository.findByDonoIdUsuario(idUser);

        return postagemEntityList.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(postagemEntityList);
    }

    @Transactional
    public ResponseEntity<PontuacaoPostagem> findPontuacaoPostagem(Long postagem, Long usuario, int pontuacao){
        PontuacaoPostagem pontuacaoPostagem = pontuacaoPostagemRepository.findByPostagemIdPostagemAndUsuarioIdUsuario(postagem, usuario).get();
        pontuacaoPostagem.setPontuacao(pontuacao);
        return  ResponseEntity.status(200).body(pontuacaoPostagem);
    }
}
