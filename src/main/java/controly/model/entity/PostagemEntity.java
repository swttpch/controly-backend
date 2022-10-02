package controly.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "tbPostagem")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbPostagem")
@Data
@SecondaryTable(name= "tbRespostaDuvida", pkJoinColumns = @PrimaryKeyJoinColumn(name = "idPostagem"))
public class PostagemEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.TABLE) @Column(name = "idPostagem")
    private Long idPostagem;

    @Column(name = "conteudo")
    private String conteudo;
    @Column(name = "criadoEm")
    private LocalDateTime criadoEm;

    @ManyToOne @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    private UsuarioEntity dono;
    @Column(name = "titulo")
    private String titulo;

    @OneToOne(cascade = CascadeType.ALL) @JoinColumn(name="idTopico", referencedColumnName = "idTopico", nullable = false)
    private TopicoEntity topico;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPostagem")
    private List<ComentarioEntity> comentarios;

    @Embedded
    private RespostaDuvidaEntity respostaDuvidaEntity;

    @OneToMany(mappedBy = "postagem") @JsonIgnore
    private Set<PontuacaoPostagem> pontuacaoPostagem = new HashSet<>();

    @JsonProperty
    public int getPontuacao(){
        int count = 0;
        for (PontuacaoPostagem pontuacao : pontuacaoPostagem){
            System.out.println(pontuacao.getPontuacao());
            count += pontuacao.getPontuacao();
            if (count < 0) count = 0;
        }
        return count;
    }
}
