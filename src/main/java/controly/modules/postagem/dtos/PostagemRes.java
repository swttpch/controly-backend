package controly.modules.postagem.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import controly.modules.comentario.entities.ComentarioEntity;
import controly.modules.perfilAndUsuario.entities.UsuarioEntity;
import controly.modules.pontuacao.entities.pontuacaoPostagem.PontuacaoPostagem;
import controly.modules.postagem.entities.RespostaDuvidaEntity;
import controly.modules.topico.entities.TopicoEntity;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class PostagemRes {

    private Long idPostagem;
    private String conteudo;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
    private UsuarioEntity dono;
    private String titulo;
    private TopicoEntity topico;
    private List<ComentarioEntity> comentarios;
    private RespostaDuvidaEntity respostaDuvidaEntity;
    private Set<PontuacaoPostagem> pontuacaoPostagem = new HashSet<>();


}
