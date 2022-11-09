package controly.modules.postagem.entities;

import controly.modules.comentario.entities.ComentarioEntity;
import controly.modules.topico.entities.TopicoEntity;
import controly.modules.perfilAndUsuario.entities.UsuarioEntity;

public abstract class Postagem {

    abstract public Long getIdUsuario();

    abstract public Long getIdTopico();

    abstract public Long getIdPostagem();

    abstract public PostagemEntity converterPostagem(TopicoEntity topico, UsuarioEntity usuario);

    abstract public ComentarioEntity converterPostagem(PostagemEntity postagem, UsuarioEntity usuario);
}
