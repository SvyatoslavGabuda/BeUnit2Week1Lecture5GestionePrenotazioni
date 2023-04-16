package it.epicode.gp.service;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import it.epicode.gp.model.Edificio;
import it.epicode.gp.model.Indirizzo;
import it.epicode.gp.repo.EdificioDaoRepo;
import it.epicode.gp.repo.IndirizzioDaoRepo;

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
	public void createAndSaveParamEdificio(String name, String citta,String via, int n) {
		
			saveEdificio(paramEdificioProvider.getObject(name,citta,via,n));
		
	}
	

	public void saveEdificio(Edificio e) {
		inRepo.save(e.getIndirizzo());
		edificioRepo.save(e);
	}

	public Edificio findEdificioById(Long id) {
		return edificioRepo.findById(id).get();
	}

	public List<Edificio> findAllEdificio() {
		return (List<Edificio>) edificioRepo.findAll();
	}

	public void removeEdificio(Edificio e) {
		edificioRepo.delete(e);
	}

}
