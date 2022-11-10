package controly.modules.perfilAndUsuario.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import controly.modules.pontuacao.entities.pontuacaoPostagem.PontuacaoPostagem;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="tb_usuario")
@Data
public class UsuarioEntity implements Serializable, UserDetails {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name= "idUsuario")
    private Long idUsuario;
    @NotNull
    private String nome;
    @NotNull
    private String apelido;
    private int avatar;
    @NotNull
    @Length(min=8)
    private String senha;
    @NotNull
    @Email
    private String email;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<RoleEntity> roles;

    @OneToMany(mappedBy = "usuario") @JsonIgnore
    private Set<PontuacaoPostagem> pontuacaoPostagem = new HashSet<>();


    public UsuarioEntity(String nome, String apelido, String senha, String email) {
        this.nome = nome;
        this.apelido = apelido;
        this.senha = new BCryptPasswordEncoder().encode(senha);
        this.email = email;
    }

    public UsuarioEntity(){}

    public void setSenha(String senha) {
        this.senha = new BCryptPasswordEncoder().encode(senha);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
