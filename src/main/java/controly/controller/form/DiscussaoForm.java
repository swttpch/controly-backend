package controly.controller.form;

import controly.model.entity.Postagem;

public class DiscussaoForm extends PostagemForm{

    private String titulo;

    private int subidas;

    public int getSubidas() {
        return subidas;
    }

    public void setSubidas(int subidas) {
        this.subidas = subidas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public Postagem converter() {
        // converte as informações para uma entidade do tipo Discussao
        return null;
    }
}
