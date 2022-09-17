package controly.controller.dto;

import controly.model.EnumUsuarioStatus;
import controly.model.entity.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class UsuarioCadastradoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nome;
    @NotNull
    private String apelido;
    @NotNull
    private String email;
    private EnumUsuarioStatus status = EnumUsuarioStatus.ATIVO;

    public UsuarioCadastradoDTO(Usuario usuario){
        this.id = usuario.getId();
        this.apelido = usuario.getApelido();
        this.email = usuario.getEmail();
        this.nome = usuario.getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EnumUsuarioStatus getStatus() {
        return status;
    }

    public void setStatus(EnumUsuarioStatus status) {
        this.status = status;
    }
}
