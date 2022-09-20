package controly.controller.form;

import controly.model.entity.Postagem;

public class ComentarioForm extends PostagemForm{
    private int curtidas;

    public int getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(int curtidas) {
        this.curtidas = curtidas;
    }

    @Override
    Postagem converter() {
        // converte as informações para uma entidade do tipo Comentario
        return null;
    }
}
