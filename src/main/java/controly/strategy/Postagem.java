package controly.strategy;

import controly.entities.CommentEntity;
import controly.entities.PostEntity;
import controly.entities.TopicEntity;
import controly.entities.UserEntity;

public abstract class Postagem {

    abstract public Long getIdUsuario();

    abstract public Long getIdTopico();

    abstract public Long getIdPostagem();

    abstract public PostEntity converterPostagem(TopicEntity topico, UserEntity usuario);

    abstract public CommentEntity converterComentario(PostEntity postagem, UserEntity usuario);
}
