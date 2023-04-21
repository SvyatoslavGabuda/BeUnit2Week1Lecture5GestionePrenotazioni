package it.epicode.gp.runner;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.epicode.gp.enums.TipoPostazione;
import it.epicode.gp.model.Indirizzo;
import it.epicode.gp.service.EdificioService;
import it.epicode.gp.service.PostazioneService;
import it.epicode.gp.service.PrenotazioneService;
import it.epicode.gp.service.UtenteService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GestionePrenotazioni implements CommandLineRunner {
	@Autowired
	UtenteService uService;
	@Autowired
	EdificioService edService;
	@Autowired
	PostazioneService posService;
	@Autowired
	PrenotazioneService preService;

	@Override
	public void run(String... args) throws Exception {
		log.warn("Runner Start");

		// Metodo che crea utenti "fake" un numero di volte
	//	uService.createAndSaveFakeUtente(100);

		// Creazione utente tramite paramentri:
		// uService.createAndSaveParamUtente("mario1993", "Mario", "ROssi",
		// "mario.rossi@example.it");

		// Creazione Edifici "fake" n numero di volte
//		edService.createAndSaveRandomEdificio(100);

		// Creazione Edificio con paramentri inseriti:
		// Scommentare una volta per poter trovare risultati della query di ricerca
		// edService.createAndSaveParamEdificio("NomeEdificio","Roma","via Appia",5 );

		// metodo che genera da 0 a 5 postazione per ogni edificio trovato nel DB
//		posService.createAndSaveRandomPostazioneForAllEdificio();

		// creazioni Prenotazioni ->
		// non tutte andranno a buon fine la prima volta è fatto apposta per testare i
		// controlli
//		preService.createAndSaveParamPrenotazione(LocalDate.of(2023,12,12), uService.findUtenteById(1l), posService.findPostazioneById(2l));
//		preService.createAndSaveParamPrenotazione(LocalDate.of(2023,12,12), uService.findUtenteById(2l), posService.findPostazioneById(2l));
//		preService.createAndSaveParamPrenotazione(LocalDate.of(2023,12,13), uService.findUtenteById(2l), posService.findPostazioneById(3l));
//		preService.createAndSaveParamPrenotazione(LocalDate.of(2023,12,12), uService.findUtenteById(3l), posService.findPostazioneById(4l));
//		preService.createAndSaveParamPrenotazione(LocalDate.of(2023,12,13), uService.findUtenteById(3l), posService.findPostazioneById(5l));
//		preService.createAndSaveParamPrenotazione(LocalDate.of(2023,12,15), uService.findUtenteById(4l), posService.findPostazioneById(6l));
//		preService.createAndSaveParamPrenotazione(LocalDate.of(2023,12,16), uService.findUtenteById(4l), posService.findPostazioneById(7l));
//		preService.createAndSaveParamPrenotazione(LocalDate.of(2023,12,16), uService.findUtenteById(4l), posService.findPostazioneById(2l));
//		preService.createAndSaveParamPrenotazione(LocalDate.of(2023,12,18), uService.findUtenteById(5l), posService.findPostazioneById(8l));
//		preService.createAndSaveParamPrenotazione(LocalDate.of(2023,12,18), uService.findUtenteById(5l), posService.findPostazioneById(4l));
//		preService.createAndSaveParamPrenotazione(LocalDate.of(2023,12,19), uService.findUtenteById(5l), posService.findPostazioneById(8l));
//		preService.createAndSaveParamPrenotazione(LocalDate.of(2023,12,19), uService.findUtenteById(6l), posService.findPostazioneById(6l));
//		preService.createAndSaveParamPrenotazione(LocalDate.of(2023,12,1), uService.findUtenteById(6l), posService.findPostazioneById(2l));
//		preService.createAndSaveParamPrenotazione(LocalDate.of(2023,12,1), uService.findUtenteById(6l), posService.findPostazioneById(6l));
//		preService.createAndSaveParamPrenotazione(LocalDate.of(2023,12,1), uService.findUtenteById(7l), posService.findPostazioneById(3l));
//		preService.createAndSaveParamPrenotazione(LocalDate.of(2023,12,1), uService.findUtenteById(7l), posService.findPostazioneById(4l));
//		preService.createAndSaveParamPrenotazione(LocalDate.of(2023,12,3), uService.findUtenteById(8l), posService.findPostazioneById(4l));
//		preService.createAndSaveParamPrenotazione(LocalDate.of(2023,12,3), uService.findUtenteById(9l), posService.findPostazioneById(6l));
//		preService.createAndSaveParamPrenotazione(LocalDate.of(2024,12,3), uService.findUtenteById(9l), posService.findPostazioneById(6l));

		// Query richiesta dalla traccia:
//		posService.findPostazioneByTipoAndCitta(TipoPostazione.PRIVATO, "Roma")
//				.forEach(p -> System.out.println(p.getId_postazione() + " " + p.getDescrizione() + " "
//						+ p.getEdificio().getNome() + " " + p.getEdificio().getIndirizzo().getCitta()));
//		;
//		
		
		
	

		log.warn("Runner End");
	}

}
