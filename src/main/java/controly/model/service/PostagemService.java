package controly.model.service;

import controly.model.entity.PontuacaoPostagem;
import controly.model.entity.PostagemEntity;
import controly.model.entity.TopicoEntity;
import controly.model.entity.UsuarioEntity;
import controly.repository.PontuacaoPostagemRepository;
import controly.repository.PostagemRepository;
import controly.repository.TopicoRepository;
import controly.repository.UsuarioRepository;
import controly.controller.form.Discussao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostagemService {
    @Autowired
    ValidationService validation;
    @Autowired
    PostagemRepository postagemRepository;
    @Autowired
    PontuacaoPostagemRepository pontuacaoPostagemRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

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

    @Transactional
    public ResponseEntity setPontuacaoPostagem(Long postagem, Long usuario, int ponto){
        if (
            !validation.existsPostagem(postagem) ||
            !validation.existsUsuario(usuario)
        ) return ResponseEntity.status(404).build();

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
        return ResponseEntity.status(200).build();
    }

    @Transactional
    public ResponseEntity<List<PostagemEntity>> getPostagemByIdUser(Long idUser){
        if (!validation.existsUsuario(idUser)) return ResponseEntity.status(404).build();

        List<PostagemEntity> postagemEntityList = postagemRepository.findAll();

        postagemEntityList.removeIf(postagem -> !postagem.getDono().getIdUsuario().equals(idUser));

        return postagemEntityList.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(postagemEntityList);
    }

}
