package controly.model.service;

import controly.controller.form.CadastrarNovaPostagemForm;
import controly.model.entity.PostagemEntity;
import controly.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostagemService {

    @Autowired
    private PostagemRepository postagemRepository;
/*
    public ResponseEntity<PostagemEntity> cadastrarPostagem(CadastrarNovaPostagemForm novaPostagem){
        PostagemEntity postagemEntity = novaPostagem.converterPostagem();
        postagemRepository.save(postagemEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(postagemEntity);
    }
    */
}
