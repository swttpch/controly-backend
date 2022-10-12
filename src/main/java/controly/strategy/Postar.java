package controly.strategy;

import controly.controller.form.Postagem;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class Postar {
    Ipostagem postagem;

    public void setPostagem(Ipostagem postagem){
        this.postagem = postagem;
    }

    public ResponseEntity<String> postar(Postagem post){
        return postagem.enviarPostagem(post);
    }


}
