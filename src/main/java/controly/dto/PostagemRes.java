package controly.dto;

import controly.entities.CommentEntity;
import controly.entities.UserEntity;
import controly.entities.PostPointsEntity;
import controly.entities.DoubtsAnswerEntity;
import controly.entities.TopicEntity;
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
    private UserEntity dono;
    private String titulo;
    private TopicEntity topico;
    private List<CommentEntity> comentarios;
    private DoubtsAnswerEntity doubtsAnswerEntity;
    private Set<PostPointsEntity> postPointEntities = new HashSet<>();


}
