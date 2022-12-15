package controly.dto;

import controly.entity.CommentEntity;
import controly.entity.UserEntity;
import controly.entity.PostPointsEntity;
import controly.entity.DoubtsAnswerEntity;
import controly.entity.TopicEntity;
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
