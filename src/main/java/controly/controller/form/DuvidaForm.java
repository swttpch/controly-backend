package controly.controller.form;

import controly.model.entity.Comentario;
import controly.model.entity.Postagem;

public class DuvidaForm extends DiscussaoForm{
    private Long id;
    private Comentario resposta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Comentario getResposta() {
        return resposta;
    }

    public void setResposta(Comentario resposta) {
        this.resposta = resposta;
    }

    @Override
    public Postagem converter() {
        // converte as informações para uma entidade do tipo Duvida
        return null;
    }
}
