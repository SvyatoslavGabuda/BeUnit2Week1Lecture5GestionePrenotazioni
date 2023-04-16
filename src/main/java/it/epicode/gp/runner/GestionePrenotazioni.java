package it.epicode.gp.runner;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
//		for(int i=0; i<30; i++) {
//			edService.createAndSaveRandomEdificio();
//		}
//		 uService.createAndSaveFakeUtente(10);
//		 posService.createAndSaveRandomPostazioneForAllEdificio();
//		preService.createAndSaveParamPrenotazione(LocalDate.of(2023,12,12), uService.findUtenteById(1l), posService.findPostazioneById(2l));
//		preService.createAndSaveParamPrenotazione(LocalDate.of(2023,12,12), uService.findUtenteById(2l), posService.findPostazioneById(2l));
//		preService.createAndSaveParamPrenotazione(LocalDate.of(2023,12,13), uService.findUtenteById(2l), posService.findPostazioneById(3l));
//		preService.createAndSaveParamPrenotazione(LocalDate.of(2023,12,12), uService.findUtenteById(3l), posService.findPostazioneById(4l));
//		preService.createAndSaveParamPrenotazione(LocalDate.of(2023,12,13), uService.findUtenteById(3l), posService.findPostazioneById(5l));
//		preService.createAndSaveParamPrenotazione(LocalDate.of(2023,12,15), uService.findUtenteById(2l), posService.findPostazioneById(6l));
	}

}
