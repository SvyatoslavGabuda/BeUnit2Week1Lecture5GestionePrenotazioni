package it.epicode.gp.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import it.epicode.gp.enums.TipoPostazione;
import it.epicode.gp.model.Postazione;
import it.epicode.gp.model.Prenotazione;
import it.epicode.gp.model.Utente;
import it.epicode.gp.service.PostazioneService;
import it.epicode.gp.service.PrenotazioneService;
import jakarta.websocket.server.PathParam;
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/prenotazione")
public class PrenotazioneController {
	@Autowired
	PrenotazioneService preService;
	@Autowired
	PostazioneService posService;

	@GetMapping
	public ResponseEntity<List<Prenotazione>> allPrenotazioni() {
		List<Prenotazione> listaPre = preService.findAllPrenotazione();
		ResponseEntity<List<Prenotazione>> resp = new ResponseEntity<List<Prenotazione>>(listaPre, HttpStatus.OK);
		return resp;
	}
	@GetMapping("/{id}")
	public ResponseEntity<Prenotazione> findPrenotazioniByid(@PathVariable Long id) {
		Prenotazione listaPre = preService.findPrenotazioneById(id);
		ResponseEntity<Prenotazione> resp = new ResponseEntity<Prenotazione>(listaPre, HttpStatus.OK);
		return resp;
	}
	@GetMapping("/pre/{id}")
	public ResponseEntity<List<Prenotazione>>findPrenotazioneby(@PathVariable Long id) {
		List<Prenotazione> listaPre = preService.findPrenotazioneByPostazione(posService.findPostazioneById(id));
		ResponseEntity<List<Prenotazione>> resp = new ResponseEntity<List<Prenotazione>>(listaPre, HttpStatus.OK);
		return resp;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> postPrenotazione(@RequestBody LocalDate data, Utente u, Postazione pos) {
		
		
		return new ResponseEntity<String>(preService.createAndSaveParamPrenotazione(data, u, pos), HttpStatus.OK);
	}
}
