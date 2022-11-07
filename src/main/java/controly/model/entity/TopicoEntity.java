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


    public TopicoEntity() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}