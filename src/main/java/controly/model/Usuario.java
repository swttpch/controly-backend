package controly.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tb_usuario")
public class Usuario {

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

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getApelido() {
        return apelido;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setId(Long id){
        this.id = id;
    }

    public EnumUsuarioStatus getStatus() {
        return status;
    }

    public void setStatus() {
        this.status = status;
    }



}
