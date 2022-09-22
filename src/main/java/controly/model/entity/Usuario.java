package controly.model.entity;

import controly.model.EnumUsuarioStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tb_usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nome;
    @NotNull
    private String apelido;
    private int avatar;
    @NotNull
    @Length(min=8)
    private String senha;
    @NotNull
    private String email;
    private EnumUsuarioStatus status = EnumUsuarioStatus.ATIVO;
    // Validar depois
    //private List<Topico> topicosQueSegue = new ArrayList<>();
}
