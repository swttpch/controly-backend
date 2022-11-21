package controly.strategy;

import controly.modules.postagem.entities.Postagem;
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

    public ResponseEntity<?> postar(Postagem post){
        return postagem.enviarPostagem(post);
    }


}
