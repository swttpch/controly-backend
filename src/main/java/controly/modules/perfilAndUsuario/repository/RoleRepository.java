package controly.modules.perfilAndUsuario.repository;

import controly.modules.perfilAndUsuario.EnumRole;
import controly.modules.perfilAndUsuario.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity,Integer> {

    RoleEntity findByEnumRole(EnumRole role);
}
