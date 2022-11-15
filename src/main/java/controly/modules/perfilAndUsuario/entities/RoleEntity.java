package controly.modules.perfilAndUsuario.entities;

import controly.modules.perfilAndUsuario.EnumRole;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "tb_role")
public class RoleEntity implements GrantedAuthority {

    @Id
    private Integer idRole;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false,unique = true)
    private EnumRole enumRole;

    public RoleEntity(){}


    @Override
    public String getAuthority() {
        return this.enumRole.toString();
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer id) {
        this.idRole = id;
    }

    public EnumRole getRoleEnum() {
        return enumRole;
    }

    public void setRoleEnum(EnumRole roleEnum) {
        this.enumRole = roleEnum;
    }
}