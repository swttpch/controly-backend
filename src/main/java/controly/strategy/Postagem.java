package controly.strategy;

import controly.entities.ComentarioEntity;
import controly.entities.PostagemEntity;
import controly.entities.TopicoEntity;
import controly.entities.UsuarioEntity;

public abstract class Postagem {

    abstract public Long getIdUsuario();

    abstract public Long getIdTopico();

    abstract public Long getIdPostagem();

    abstract public PostagemEntity converterPostagem(TopicoEntity topico, UsuarioEntity usuario);

    abstract public ComentarioEntity converterComentario(PostagemEntity postagem, UsuarioEntity usuario);
}
