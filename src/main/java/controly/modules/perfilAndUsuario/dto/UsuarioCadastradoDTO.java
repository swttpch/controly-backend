package controly.modules.perfilAndUsuario.dto;

import controly.modules.perfilAndUsuario.entities.UsuarioEntity;

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

    public UsuarioCadastradoDTO(UsuarioEntity usuarioEntity){
        this.id = usuarioEntity.getIdUsuario();
        this.apelido = usuarioEntity.getApelido();
        this.email = usuarioEntity.getEmail();
        this.nome = usuarioEntity.getNome();
    }

    public Long getIdUsuario() {
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

}
