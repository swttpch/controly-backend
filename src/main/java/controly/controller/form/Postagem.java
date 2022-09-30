package controly.controller.form;

import controly.model.entity.ComentarioEntity;
import controly.model.entity.PostagemEntity;
import controly.model.entity.TopicoEntity;
import controly.model.entity.UsuarioEntity;

public abstract class Postagem {

    abstract public Long getIdUsuario();

    abstract public Long getIdTopico();

    abstract public Long getIdPostagem();

    abstract public PostagemEntity converterPostagem(TopicoEntity topico, UsuarioEntity usuario);

    abstract public ComentarioEntity converterPostagem(PostagemEntity postagem, UsuarioEntity usuario);
}
