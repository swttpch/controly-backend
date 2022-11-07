package controly.comentario.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import controly.pontuacao.entities.PontuacaoComentario;
import controly.perfilAndUsuario.entities.UsuarioEntity;
import controly.postagem.entities.PostagemEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity @Table(name = "tbComentario") @Data @NoArgsConstructor @AllArgsConstructor
public class ComentarioEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "idComentario")
    private Long idComentario;
    @Column(name = "conteudo")
    private String conteudo;
    @Column(name = "criadoEm")
    private LocalDateTime criadoEm;
    @Column(name = "atualizadoEm") @Nullable
    private LocalDateTime atualizadoEm;
    @ManyToOne(cascade=CascadeType.ALL) @JoinColumn(name = "idPostagem", referencedColumnName = "idPostagem")@JsonIgnore
    private PostagemEntity postagem;

    @OneToMany(mappedBy = "comentario",cascade=CascadeType.ALL) @JsonIgnore
    private Set<PontuacaoComentario> pontuacaoComentarios = new HashSet<>();

    @ManyToOne @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    private UsuarioEntity dono;

    @JsonProperty
    public int getPontuacao(){
        return pontuacaoComentarios.stream().mapToInt(PontuacaoComentario::getPontuacao).sum();
    }
}
