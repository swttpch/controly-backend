package controly.model.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "tbPostagem")
@Table(name = "tbPostagem")
@SecondaryTable(name= "tbRespostaDuvida", pkJoinColumns = @PrimaryKeyJoinColumn(name = "idPostagem"))
public class DiscussaoEntity extends PostagemEntity{
    @Column(name = "titulo")
    private String titulo;
    @ManyToMany
    @JoinTable(name = "postagemHasSubidas", joinColumns =
            {@JoinColumn(name = "idPostagem")}, inverseJoinColumns =
            {@JoinColumn(name= "idUsuario")})
    private List<UsuarioEntity> subidas;
    @OneToOne(cascade = CascadeType.ALL) @JoinColumn(name="idTopico", referencedColumnName = "idTopico", nullable = false)
    private TopicoEntity topico;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPostagem")
    private List<ComentarioEntity> comentarios;

    @Embedded
    private RespostaDuvidaEntity respostaDuvidaEntity;

    public DiscussaoEntity(String conteudo, Date criadoEm, String titulo, TopicoEntity topico) {
        super(conteudo, criadoEm);
        this.titulo = titulo;
        this.topico= topico;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<UsuarioEntity> getSubidas() {
        return subidas;
    }

    public void setSubidas(List<UsuarioEntity> subidas) {
        this.subidas = subidas;
    }

    public TopicoEntity getTopico() {
        return topico;
    }

    public void setTopico(TopicoEntity topico) {
        this.topico = topico;
    }

    public List<ComentarioEntity> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioEntity> comentarios) {
        this.comentarios = comentarios;
    }
}
