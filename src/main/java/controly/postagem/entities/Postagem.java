package controly.postagem.entities;

import controly.comentario.entities.ComentarioEntity;
import controly.topico.entities.TopicoEntity;
import controly.perfilAndUsuario.entities.UsuarioEntity;

public abstract class Postagem {

    abstract public Long getIdUsuario();

    abstract public Long getIdTopico();

    abstract public Long getIdPostagem();

    abstract public PostagemEntity converterPostagem(TopicoEntity topico, UsuarioEntity usuario);

    abstract public ComentarioEntity converterPostagem(PostagemEntity postagem, UsuarioEntity usuario);
}
