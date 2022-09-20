package controly.model.entity;

public class Discussao extends Postagem{
    private String titulo;
    private int subidas;
    public Discussao(String titulo, String conteudo) {
        super(conteudo);
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getSubidas() {
        return subidas;
    }

    public void setSubidas(int subidas) {
        this.subidas = subidas;
    }
}
