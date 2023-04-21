package it.epicode.gp.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.epicode.gp.model.Utente;

@Repository
public interface UtenteDaoRepo extends CrudRepository<Utente, Long>,JpaRepository<Utente, Long>{
Optional<Utente> findByUsernameOrEmail(String username,String email);
Boolean existsByUsername(String username);

Boolean existsByEmail(String email);
}
