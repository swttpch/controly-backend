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
import java.util.List;

@Service
public class PostagemService {
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
        PostagemEntity postagem = postagemRepository.findById(id).get();
        return ResponseEntity.status(200).body(postagem);
    }

    @Transactional
    public ResponseEntity setPontuacaoPostagem(Long postagem, Long usuario, int ponto){
        if (pontuacaoPostagemRepository.findByPostagemAndUsuario(postagem, usuario) == null){
            PontuacaoPostagem pontuacao = new PontuacaoPostagem();
            pontuacao.setPostagem(postagemRepository.findById(postagem).get());
            pontuacao.setUsuario(usuarioRepository.findById(usuario).get());
            pontuacao.setPontuacao(ponto);
            pontuacaoPostagemRepository.save(pontuacao);
            return ResponseEntity.status(200).build();
        } else {
            pontuacaoPostagemRepository.setPontuacaoFor(postagem, usuario, ponto);
            return ResponseEntity.status(200).build();
        }
    }
}
