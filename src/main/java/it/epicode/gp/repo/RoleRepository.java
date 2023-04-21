package it.epicode.gp.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.gp.enums.RoleType;
import it.epicode.gp.model.Role;



public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByRoleType(RoleType r);
}
