package it.epicode.gp.service;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import it.epicode.gp.model.Utente;
import it.epicode.gp.repo.UtenteDaoRepo;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UtenteService {
	@Autowired
	private UtenteDaoRepo uRepo;
	@Autowired
	@Qualifier("FakeUser")
	private ObjectProvider<Utente> fakeUtenteProvider;
	@Autowired
	@Qualifier("ParamsUser")
	private ObjectProvider<Utente> paramUtenteProvider;

	public void createAndSaveFakeUtente(int n) {
		for (int i = 0; i < n; i++) {
			saveUtente(fakeUtenteProvider.getObject());
		}
	}

	public void createAndSaveParamUtente(String username, String name, String lastname, String email) {

		saveUtente(paramUtenteProvider.getObject(username, name, lastname, email));

	}

	public void saveUtente(Utente u) {
		uRepo.save(u);

	}

	public Utente createUtente(Utente u) {
		saveUtente(u);
		return u;
	}

	public void updateUtente(Utente u) {
		if (!uRepo.existsById(u.getId_utente())) {
			throw new EntityNotFoundException("User not exists!!!");
		} else {
			uRepo.save(u);
		}
	}

	public Utente findUtenteById(Long id) {
		if (!uRepo.existsById(id)) {
			throw new EntityNotFoundException("User not exists!!!");
		} else {
			return uRepo.findById(id).get();
		}
	}

	public List<Utente> findAllUtente() {
		return (List<Utente>) uRepo.findAll();
	}

	public void removeUtente(Utente u) {
		if (!uRepo.existsById(u.getId_utente())) {
			throw new EntityNotFoundException("User not exists!!!");
		} else {
			uRepo.delete(u);
		}
	}

	public String removeUtenteById(Long id) {
		if (!uRepo.existsById(id)) {
			throw new EntityNotFoundException("User not exists!!!");
		} else {
			uRepo.deleteById(id);
			return "Utente eliminato";
		}
	}

}
