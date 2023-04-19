package it.epicode.gp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.epicode.gp.model.Prenotazione;
import it.epicode.gp.service.PrenotazioneService;

@Controller
@RequestMapping("/prenotazione")
public class PrenotazioneController {
	@Autowired
	PrenotazioneService preService;

	@GetMapping
	public ResponseEntity<List<Prenotazione>> allPrenotazioni() {
		List<Prenotazione> listaPre = preService.findAllPrenotazione();
		ResponseEntity<List<Prenotazione>> resp = new ResponseEntity<List<Prenotazione>>(listaPre, HttpStatus.OK);
		return resp;
	}
}
