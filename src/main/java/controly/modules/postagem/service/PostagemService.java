package controly.modules.postagem.service;

import controly.modules.perfilAndUsuario.entities.UsuarioEntity;
import controly.modules.perfilAndUsuario.repository.UsuarioRepository;
import controly.modules.postagem.pontuacao.entities.pontuacaoPostagem.PontuacaoPostagem;
import controly.modules.postagem.entities.PostagemEntity;
import controly.modules.postagem.repository.PontuacaoPostagemRepository;
import controly.modules.postagem.repository.PostagemRepository;
import controly.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

import static controly.config.Constant.IDNOTFOUND;

@Service
public class PostagemService {
    @Autowired
    private ValidationService validation;
    @Autowired
    private PostagemRepository postagemRepository;
    @Autowired
    private PontuacaoPostagemRepository pontuacaoPostagemRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public PostagemService() {
    }

    @Transactional
    public ResponseEntity<List<PostagemEntity>> todasPostagens() {
        List<PostagemEntity> postagemEntities = postagemRepository.findAll();
        if (postagemEntities.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(postagemEntities);
    }

    @Transactional
    public ResponseEntity<PostagemEntity> pegarPostagemPeloId(Long id){
        if (validation.existsPostagem(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Postagem não encontrada");
        }
        PostagemEntity postagem = postagemRepository.findByIdPostagem(id);
        return ResponseEntity.status(200).body(postagem);
    }

    public ResponseEntity<String> setPontuacaoPostagem(Long postagem, Long usuario, int ponto){
        UsuarioEntity usuarioEntity = usuarioRepository.findByIdUsuario(usuario).orElseThrow();
        if (
            validation.existsPostagem(postagem) ||
                    validation.existsUsuario(usuario)
        ) return ResponseEntity.status(404).body(IDNOTFOUND);

        if (!validation.existsPontuacaoWithPostagemAndUsuario(postagem, usuario)){
            pontuacaoPostagemRepository.save(
                    new PontuacaoPostagem()
                            .setPostagem(postagemRepository.findByIdPostagem(postagem))
                            .setUsuario(usuarioEntity)
                            .setPontuacao(ponto)
            );
        } else {
            pontuacaoPostagemRepository.setPontuacaoFor(postagem, usuario, ponto);
        }
        return ResponseEntity.status(200).body(String.format("Pontuação %d atribuida a postagem de ID %d.", ponto, postagem));
    }
    @Transactional
    public ResponseEntity<String> excluirPostagem(Long idPostagem) {
        if (validation.existsPostagem(idPostagem)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Postagem não encontrada");
        }
        PostagemEntity postagem = postagemRepository.findByIdPostagem(idPostagem);
        //postagemRepository.delete(postagem);
        int result = postagemRepository.deleteByIdPostagem(idPostagem);
        return ResponseEntity.status(200).body("Postagem de ID "+idPostagem+" excluida. nº: " + result);
    }

    @Transactional
    public ResponseEntity<List<PostagemEntity>> getPostagemByIdUser(Long idUser){
        if (validation.existsUsuario(idUser)) return ResponseEntity.status(404).build();

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
