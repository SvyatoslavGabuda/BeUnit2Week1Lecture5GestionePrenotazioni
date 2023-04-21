package it.epicode.gp.service;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.gp.enums.StatoPostazione;
import it.epicode.gp.enums.TipoPostazione;
import it.epicode.gp.model.Edificio;
import it.epicode.gp.model.Postazione;
import it.epicode.gp.repo.EdificioDaoRepo;
import it.epicode.gp.repo.PostazioneDaoRepo;
import jakarta.persistence.EntityExistsException;

@Service
public class PostazioneService {
	@Autowired
	PostazioneDaoRepo posRepo;
	@Autowired 
	EdificioDaoRepo edRepo;
	@Autowired
		@Qualifier("RandomPostazione")
	private ObjectProvider<Postazione> randomPostazioneProvider;
	@Autowired
	@Qualifier("ParamPostazione")
	private ObjectProvider<Postazione> paramsPostazioneProvider;
	
	public void createAndSavePostazioneParams(String desc, int n, StatoPostazione statop, TipoPostazione tipop, Edificio e) {
		try {
			savePostazione(paramsPostazioneProvider.getObject(desc,  n,  statop,  tipop,  e));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void createAndSaveRandomPostazioneForAllEdificio() {
		List<Edificio> listaEd = (List<Edificio>) edRepo.findAll();
		for(int i = 0; i<listaEd.size(); i++) {
			int randN = (int) (Math.random()*5);
			for(int j = 0; j<=randN;j++) {
				
				try {
					savePostazione(randomPostazioneProvider.getObject(listaEd.get(i)));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	public String savePostazione(Postazione p) throws Exception {
		try {
			posRepo.save(p);
			return"Postazione salvata";
		} catch (Exception e) {
			throw new Exception();
		} 
	}
	public Postazione findPostazioneById(Long id) {
		return posRepo.findById(id).get();
	}
	public List<Postazione> findAllPostazione(){
		return (List<Postazione>) posRepo.findAll();
	}
	public void removePostazione(Postazione p) {
	posRepo.delete(p);
	}
	public String removePostazioneById(Long id) {
		if(!posRepo.existsById(id)) {
			throw new EntityExistsException("Postazione not exists!!!");
		}
		posRepo.deleteById(id);
		return "Postazione eliminata";
	}
	public List<Postazione> findPostazioneByTipo(TipoPostazione t) {
		return posRepo.findByTipoPostazione(t);
	}
	public Page<Postazione> findPostazioneByTipoPag(TipoPostazione t,Pageable pag) {
		return posRepo.findByTipoPostazione(t,pag);
	}
	public List<Postazione> findPostazioneByTipoAndCitta(TipoPostazione t,String citta) {
		return posRepo.findPostazioneByTipoAndCitta(t,citta);
	}
}
