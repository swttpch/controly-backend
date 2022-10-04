package controly.model.entity;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.bytebuddy.utility.nullability.MaybeNull;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name="tbTopico")
@AllArgsConstructor
@Data
public class TopicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTopico;

    @NotNull
    private String nome;

    @NotNull
    private String descricao;

    @ManyToMany
    @JoinTable(name = "TopicoHasSeguidores", joinColumns =
            {@JoinColumn(name = "idTopico")}, inverseJoinColumns =
            {@JoinColumn(name= "idUsuario")})
    private Set<UsuarioEntity> seguidores;

    public int getSeguidores() {
        return seguidores != null ? seguidores.size() : 0;
    }

    public void addSeguidores(UsuarioEntity usuario) {
            seguidores.add(usuario);

    }

    public TopicoEntity() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}