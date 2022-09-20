package controly.model.entity;

public class Comentario extends Postagem {

    private int curtidas;

    public Comentario(String conteudo) {
        super(conteudo);
    }

    public int getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(int curtidas) {
        this.curtidas = curtidas;
    }
}
