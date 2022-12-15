package controly.strategy;

import controly.entity.CommentEntity;
import controly.entity.PostEntity;
import controly.entity.TopicEntity;
import controly.entity.UserEntity;

public abstract class Postagem {

    abstract public Long getIdUsuario();

    abstract public Long getIdTopico();

    abstract public Long getIdPostagem();

    abstract public PostEntity converterPostagem(TopicEntity topico, UserEntity usuario);

    abstract public CommentEntity converterComentario(PostEntity postagem, UserEntity usuario);
}
