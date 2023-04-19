package it.epicode.gp.repo;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import it.epicode.gp.model.Edificio;
@Repository
public interface EdificioDaoRepo extends PagingAndSortingRepository<Edificio, Long>,CrudRepository<Edificio, Long> {

	
}
