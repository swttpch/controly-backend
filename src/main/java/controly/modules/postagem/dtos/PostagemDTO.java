package controly.modules.postagem.dtos;

import controly.modules.perfilAndUsuario.entities.UsuarioEntity;
import controly.modules.postagem.entities.PostagemEntity;
import controly.modules.topico.entities.TopicoEntity;
import lombok.Data;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
public class PostagemDTO {

    @Id
    private Long idPostagem = new PostagemEntity().getIdPostagem();

    private String titulo = new PostagemEntity().getTitulo();

    private String conteudo = new PostagemEntity().getConteudo();

    private LocalDateTime atualizadoEm = new PostagemEntity().getAtualizadoEm();

    private UsuarioEntity dono = new PostagemEntity().getDono();

    private TopicoEntity topico = new PostagemEntity().getTopico();

    private Integer pontuacaoPostagem = new PostagemEntity().getPontuacao();
}
