package it.epicode.gp.service;


import java.util.List;

import org.hibernate.boot.model.source.internal.hbm.CompositeIdentifierSingularAttributeSourceManyToOneImpl;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.gp.model.Edificio;
import it.epicode.gp.model.Indirizzo;
import it.epicode.gp.repo.EdificioDaoRepo;
import it.epicode.gp.repo.IndirizzioDaoRepo;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EdificioService {
	@Autowired
	private EdificioDaoRepo edificioRepo;
	@Autowired
	private IndirizzioDaoRepo inRepo;
	@Autowired
	@Qualifier("RandomEdificio")
	private ObjectProvider<Edificio> randomEdificioProvider;
	@Autowired
	@Qualifier("ParamEdificio")
	private ObjectProvider<Edificio> paramEdificioProvider;

	public void createAndSaveRandomEdificio(int n) {
		for (int i = 0; i < n; i++) {
			saveEdificio(randomEdificioProvider.getObject());
		}
	}
	public void createAndSaveRandomEdificio2(int n) {
		for (int i = 0; i < n; i++) {
			saveEdificioAndIndirizzo(randomEdificioProvider.getObject());
		}
	}
	public void createAndSaveParamEdificio(String name, String citta,String via, int n) {
		
			saveEdificio(paramEdificioProvider.getObject(name,citta,via,n));
		
	}
	

	public  void saveEdificio(Edificio e) {
		inRepo.save(e.getIndirizzo());
		edificioRepo.save(e);
		
	}
	public void upDateEdificio(Edificio e) {
		edificioRepo.save(e);
	}
	public String saveEdificioAndIndirizzo(Edificio e) {
		Indirizzo i = inRepo.save(e.getIndirizzo());
		System.out.println(i);
		e.setIndirizzo(i);
		edificioRepo.save(e);
		return "edificio salvato";
	}

	public Edificio findEdificioById(Long id) {
		if(!edificioRepo.existsById(id)) {
			throw new EntityNotFoundException("edificio non trovato");
		}
		return edificioRepo.findById(id).get();
	}

	public List<Edificio> findAllEdificio() {
		return (List<Edificio>) edificioRepo.findAll();
	}

	public void removeEdificio(Edificio e) {
		edificioRepo.delete(e);
	}
	public String removeEdificioById(Long id) {
		if(!edificioRepo.existsById(id)) {
			throw new EntityNotFoundException("edificio non trovato");
		}else {
			
			edificioRepo.deleteById(id);
			return "Edificio Eliminato";
		}
		
	}
	public Page<Edificio> findAllEdificioPageble(Pageable pag){
		
			 Page<Edificio> res = edificioRepo.findAll(pag);
			return res;
		

	}

}
