package controly.modules.postagem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import controly.modules.comentario.entities.ComentarioEntity;
import controly.modules.perfilAndUsuario.entities.UsuarioEntity;
import controly.modules.postagem.pontuacao.entities.pontuacaoPostagem.PontuacaoPostagem;
import controly.modules.topico.entities.TopicoEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "tbPostagem")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbPostagem")
@SecondaryTable(name= "tbRespostaDuvida", pkJoinColumns = @PrimaryKeyJoinColumn(name = "idPostagem"))
public class PostagemEntity {

    @Id @GeneratedValue(strategy = GenerationType.TABLE) @Column(name = "idPostagem")
    private Long idPostagem;

    @Column(name = "conteudo")
    private String conteudo;
    @Column(name = "criadoEm")
    private LocalDateTime criadoEm;
    @Column(name = "atualizadoEm") @Nullable
    private LocalDateTime atualizadoEm;

    @ManyToOne @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    private UsuarioEntity dono;
    @Column(name = "titulo")
    private String titulo;

    @OneToOne @JoinColumn(name="idTopico", referencedColumnName = "idTopico", nullable = false)
    private TopicoEntity topico;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPostagem")
    private List<ComentarioEntity> comentarios;

    @Embedded
    private RespostaDuvidaEntity respostaDuvidaEntity;

    @OneToMany(mappedBy = "postagem",cascade=CascadeType.ALL) @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<PontuacaoPostagem> pontuacaoPostagem = new HashSet<>();


    @JsonProperty
    public int getPontuacao(){
        return pontuacaoPostagem.stream().mapToInt(PontuacaoPostagem::getPontuacao).sum();
    }

    public void setResposta(ComentarioEntity resposta){
        respostaDuvidaEntity
                .setResposta(resposta)
                .setResolvido(true)
                .setResolvidoEm(LocalDateTime.now());
    }

    public PostagemEntity initResposta(){
        respostaDuvidaEntity = new RespostaDuvidaEntity().setResolvido(false);
        return this;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    @Nullable
    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(@Nullable LocalDateTime atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }

    public UsuarioEntity getDono() {
        return dono;
    }

    public void setDono(UsuarioEntity dono) {
        this.dono = dono;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public RespostaDuvidaEntity getRespostaDuvidaEntity() {
        return respostaDuvidaEntity;
    }

    public void setRespostaDuvidaEntity(RespostaDuvidaEntity respostaDuvidaEntity) {
        this.respostaDuvidaEntity = respostaDuvidaEntity;
    }

    public Long getIdPostagem() {
        return idPostagem;
    }

    public void setIdPostagem(Long idPostagem) {
        this.idPostagem = idPostagem;
    }
}
