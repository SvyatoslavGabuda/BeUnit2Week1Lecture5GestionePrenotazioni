package it.epicode.gp.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import it.epicode.gp.enums.StatoPostazione;
import it.epicode.gp.enums.TipoPostazione;
import it.epicode.gp.model.Postazione;
import it.epicode.gp.model.Prenotazione;
import it.epicode.gp.model.Utente;
import it.epicode.gp.repo.PrenotazioneDaoRepo;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PrenotazioneService {
	@Autowired
	PrenotazioneDaoRepo preRepo;
	@Autowired
	@Qualifier("ParamPrenotazione")
	private ObjectProvider<Prenotazione> paramPrenotazioneProvider;

	public void createAndSaveParamPrenotazione(LocalDate data, Utente u, Postazione pos) {
		// se controllare se la postazione è gia prenotata per quella data
		// controllare se l'uteten ha gia una prenotazione per quella data

		// controllare se la data non è passata
		// controllare se la postazione è libera
		if (data.isAfter(LocalDate.now())) {
			if (pos.getStatoPostazione() == StatoPostazione.LIBERO) {
				if (findPrenotazioneByDataAndPostazione(data, pos) == null) {
					if (findPrenotazioneByDataAndUtente(data, u) == null) {
						savePrenotazione(paramPrenotazioneProvider.getObject(data, u, pos));
						System.out.println("Prenotazione eseguita con sucesso");
					} else {
						log.error("L' utente ha gia una prenotazione per quella data");
					}

				} else {
					log.error("La postazione è gia PRENOTATA per la data richiesta");
				}
			} else {
				log.error("la Postazione NON è disponibile");
			}
		} else {
			log.error("la DATA inserita è già passata, non è previsto che puoi viaggiare nel tempo.");
		}

	}

// private perchè le prenotazioni per essere create sono soggete a controlli
	private void savePrenotazione(Prenotazione pre) {
		preRepo.save(pre);

	}

	public Prenotazione findPrenotazioneById(Long id) {
		return preRepo.findById(id).get();
	}

	public List<Prenotazione> findAllPrenotazione() {
		return (List<Prenotazione>) preRepo.findAll();
	}

	public void removePrenotazione(Prenotazione pre) {
		preRepo.delete(pre);
	}

	public void removePrenotazioneById(Long id) {
		preRepo.deleteById(id);
	}

	public List<Prenotazione> findPrenotazioneByData(LocalDate data) {
		return (List<Prenotazione>) preRepo.findByDataPrenotazione(data);
	}

	public List<Prenotazione> findPrenotazioneByUtente(Utente u) {
		return (List<Prenotazione>) preRepo.findByUtente(u);
	}

	public List<Prenotazione> findPrenotazioneByPostazione(Postazione pos) {
		return (List<Prenotazione>) preRepo.findByPostazione(pos);
	}

	public Prenotazione findPrenotazioneByDataAndPostazione(LocalDate data, Postazione pos) {
		return preRepo.findByDataPrenotazioneAndPostazione(data, pos);
	}

	public Prenotazione findPrenotazioneByDataAndUtente(LocalDate data, Utente u) {
		return preRepo.findByDataPrenotazioneAndUtente(data, u);
	}

}
