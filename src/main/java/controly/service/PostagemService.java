package controly.service;

import controly.entities.UserEntity;
import controly.repository.UserRepository;
import controly.dto.PostagemDTO;
import controly.entities.PostPointsEntity;
import controly.entities.PostEntity;
import controly.repository.PostPointsRepository;
import controly.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static controly.config.Constant.IDNOTFOUND;

@Service
public class PostagemService {
    @Autowired
    private ValidationService validation;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostPointsRepository postPointsRepository;

    @Autowired
    private UserRepository userRepository;

    public PostagemService() {
    }

    @Transactional
    public ResponseEntity<List<PostagemDTO>> todasPostagens() {

        List<PostEntity> postagens = postRepository.findAll();

        if (postagens.isEmpty()) return ResponseEntity.status(204).build();

        List<PostagemDTO> postagemDTOlist = new ArrayList<>();

        for (PostEntity postagem : postagens) {

            PostagemDTO postagemDT = new PostagemDTO();

            postagemDT.setIdPostagem(postagem.getIdPost());
            postagemDT.setTitulo(postagem.getTitle());
            postagemDT.setConteudo(postagem.getContent());
            postagemDT.setTopico(postagem.getTopic());
            postagemDT.setPontuacaoPostagem(postagem.getPoints());

            postagemDT.setDono(postagem.getOwner());
            postagemDTOlist.add(postagemDT);

        }

        return ResponseEntity.status(200).body(postagemDTOlist);

    }

    @Transactional
    public ResponseEntity<PostEntity> pegarPostagemPeloId(Long id){
        if (validation.existsPostagem(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Postagem não encontrada");
        }
        PostEntity postagem = postRepository.findByIdPost(id);
        return ResponseEntity.status(200).body(postagem);
    }

    public ResponseEntity<String> setPontuacaoPostagem(Long postagem, Long usuario, int ponto){
        UserEntity userEntity = userRepository.findByIdUser(usuario).orElseThrow();
        if (
            validation.existsPostagem(postagem) ||
                    validation.existsUsuario(usuario)
        ) return ResponseEntity.status(404).body(IDNOTFOUND);

        if (!validation.existsPontuacaoWithPostagemAndUsuario(postagem, usuario)){
            postPointsRepository.save(
                    new PostPointsEntity()
                            .setPost(postRepository.findByIdPost(postagem))
                            .setUser(userEntity)
                            .setPoints(ponto)
            );
        } else {
            postPointsRepository.setPointsFor(postagem, usuario, ponto);
        }
        return ResponseEntity.status(200).body(String.format("Pontuação %d atribuida a postagem de ID %d.", ponto, postagem));
    }
    @Transactional
    public ResponseEntity<String> excluirPostagem(Long idPostagem) {
        if (validation.existsPostagem(idPostagem)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Postagem não encontrada");
        }
        PostEntity postagem = postRepository.findByIdPost(idPostagem);
        //postagemRepository.delete(postagem);
        int result = postRepository.deleteByIdPost(idPostagem);
        return ResponseEntity.status(200).body("Postagem de ID "+idPostagem+" excluida. nº: " + result);
    }

    @Transactional
    public ResponseEntity<List<PostEntity>> getPostagemByIdUser(Long idUser){
        if (validation.existsUsuario(idUser)) return ResponseEntity.status(404).build();

        List<PostEntity> postEntityList = postRepository.findByOwnerIdUser(idUser);

        return postEntityList.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(postEntityList);
    }

    @Transactional
    public ResponseEntity<PostPointsEntity> findPontuacaoPostagem(Long postagem, Long usuario, int pontuacao){
        PostPointsEntity postPointsEntity = postPointsRepository.findByPostIdPostAndUserIdUser(postagem, usuario).get();
        postPointsEntity.setPoints(pontuacao);
        return  ResponseEntity.status(200).body(postPointsEntity);
    }
}
