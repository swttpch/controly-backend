package controly.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity(name = "tbPostagem")
@Table(name = "tbPostagem")
@SecondaryTable(name= "tbRespostaDuvida", pkJoinColumns = @PrimaryKeyJoinColumn(name = "idPostagem"))
public class PostagemEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.TABLE) @Column(name = "idPostagem")
    private Long idPostagem;

    @Column(name = "conteudo")
    private String conteudo;
    @Column(name = "criadoEm")
    private java.sql.Date criadoEm;

    @ManyToOne @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    private UsuarioEntity dono;
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

    public PostagemEntity(String conteudo, String titulo, TopicoEntity topico, UsuarioEntity dono) {
        long millis=System.currentTimeMillis();
        this.conteudo = conteudo;
        this.criadoEm = new java.sql.Date(millis);
        this.titulo = titulo;
        this.topico= topico;
        this.dono = dono;
    }

    public PostagemEntity(){

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

    public Long getIdPostagem() {
        return idPostagem;
    }

    public void setIdPostagem(Long idPostagem) {
        this.idPostagem = idPostagem;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Date getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Date criadoEm) {
        this.criadoEm = criadoEm;
    }

    public UsuarioEntity getDono() {
        return dono;
    }

    public void setDono(UsuarioEntity dono) {
        this.dono = dono;
    }

    public RespostaDuvidaEntity getRespostaDuvidaEntity() {
        return respostaDuvidaEntity;
    }

    public void setRespostaDuvidaEntity(RespostaDuvidaEntity respostaDuvidaEntity) {
        this.respostaDuvidaEntity = respostaDuvidaEntity;
    }
}
