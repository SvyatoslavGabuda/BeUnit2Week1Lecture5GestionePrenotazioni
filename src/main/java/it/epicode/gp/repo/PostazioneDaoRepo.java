package it.epicode.gp.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import it.epicode.gp.enums.TipoPostazione;
import it.epicode.gp.model.Edificio;

import it.epicode.gp.model.Postazione;

@Repository
public interface PostazioneDaoRepo extends CrudRepository<Postazione, Long>,PagingAndSortingRepository<Postazione, Long> {
	
	public List<Postazione> findByTipoPostazione(TipoPostazione tipoPostazione);
	
	public Page<Postazione> findByTipoPostazione(TipoPostazione tipoPostazione,Pageable pag);

	public List<Postazione> findByEdificio(Edificio edificio);
	
	@Query(value = "SELECT p FROM Postazione p WHERE p.tipoPostazione = :tipo "
			+ "AND p.edificio IN (Select e FROM Edificio e WHERE e.indirizzo IN("
			+ "SELECT i FROM Indirizzo i WHERE i.citta = :citta))")
	public List<Postazione> findPostazioneByTipoAndCitta(TipoPostazione tipo, String citta);
	

}
