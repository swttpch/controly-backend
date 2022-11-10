package controly.modules.perfilAndUsuario.entities;

import controly.modules.perfilAndUsuario.EnumRole;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "tb_role")
public class RoleEntity implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false,unique = true)
    private EnumRole enumRole;

    @Override
    public String getAuthority() {
        return this.enumRole.toString();
    }

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long id) {
        this.idRole = id;
    }

    public EnumRole getRoleEnum() {
        return enumRole;
    }

    public void setRoleEnum(EnumRole roleEnum) {
        this.enumRole = roleEnum;
    }
}