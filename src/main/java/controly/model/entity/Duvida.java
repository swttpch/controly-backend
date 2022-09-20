package controly.model.entity;

public class Duvida extends Discussao{

    private Comentario resposta;

    public Duvida(String titulo, String conteudo) {
        super(titulo, conteudo);
    }

    public Comentario getResposta() {
        return resposta;
    }

    public void setResposta(Comentario resposta) {
        this.resposta = resposta;
    }
}
