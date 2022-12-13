package controly.dto;

import controly.entities.ComentarioEntity;
import controly.entities.UsuarioEntity;
import controly.entities.PontuacaoPostagem;
import controly.entities.RespostaDuvidaEntity;
import controly.entities.TopicoEntity;
import lombok.Data;

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
