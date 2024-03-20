package teamproject.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teamproject.project.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(Role.RoleName roleName);
}
