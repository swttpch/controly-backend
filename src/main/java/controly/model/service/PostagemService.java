package controly.model.service;

import controly.controller.form.CadastrarNovaPostagemForm;
import controly.model.entity.PostagemEntity;
import controly.model.entity.TopicoEntity;
import controly.model.entity.UsuarioEntity;
import controly.repository.PostagemRepository;
import controly.repository.TopicoRepository;
import controly.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PostagemService {

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public PostagemEntity cadastrarPostagem(CadastrarNovaPostagemForm novaPostagem){
        TopicoEntity topico = topicoRepository.findByIdTopico(novaPostagem.getIdTopico()).get();
        UsuarioEntity usuario = usuarioRepository.findById(novaPostagem.getIdUsuario()).get();
        PostagemEntity postagemEntity = novaPostagem.converterPostagem(topico, usuario);
        postagemRepository.save(postagemEntity);
        return postagemEntity;
    }

    @Transactional
    public List<PostagemEntity> buscarTodasPostagens(){
        return postagemRepository.findAll();
    }
}
