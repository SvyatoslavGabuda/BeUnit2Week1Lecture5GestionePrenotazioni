package it.epicode.gp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import it.epicode.gp.model.Postazione;
import it.epicode.gp.service.PostazioneService;

@Controller
@RequestMapping("/postazione")
public class PostazioneController {
	@Autowired
	PostazioneService posService;

	@GetMapping
	public ResponseEntity<List<Postazione>> allPostazioni() {
		List<Postazione> listaPos = posService.findAllPostazione();
		ResponseEntity<List<Postazione>> resp = new ResponseEntity<List<Postazione>>(listaPos, HttpStatus.OK);
		return resp;
	}
	@GetMapping("/{id}")
	public ResponseEntity<Postazione> allPostazioni(@PathVariable Long id) {
		Postazione pos = posService.findPostazioneById(id);
		ResponseEntity<Postazione> resp = new ResponseEntity<Postazione>(pos, HttpStatus.OK);
		return resp;
	}
}
