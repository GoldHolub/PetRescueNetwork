package pet.network.demo.repository.role;

import org.springframework.data.jpa.repository.JpaRepository;
import pet.network.demo.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(Role.RoleName roleName);
}
