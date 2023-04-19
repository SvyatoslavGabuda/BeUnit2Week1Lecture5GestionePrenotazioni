package it.epicode.gp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import it.epicode.gp.model.Edificio;
import it.epicode.gp.repo.IndirizzioDaoRepo;
import it.epicode.gp.service.EdificioService;

@Controller
@RequestMapping("/edificio")
public class EdificioController {
	@Autowired
	EdificioService edService;
	
	@GetMapping
	public ResponseEntity<List<Edificio>> allEdifici() {
		List<Edificio> listaEdifici = edService.findAllEdificio();
		ResponseEntity<List<Edificio>> resp = new ResponseEntity<List<Edificio>>(listaEdifici, HttpStatus.OK);
		return resp;
	}
	@GetMapping("/pag")
	public ResponseEntity<Page<Edificio>> allEdificiPag(Pageable pag) {
		Page<Edificio>listaEdifici = edService.findAllEdificioPageble(pag);
		ResponseEntity<Page<Edificio>> resp = new ResponseEntity<Page<Edificio>>(listaEdifici, HttpStatus.OK);
		return resp;
	}
	@GetMapping("/{id}")
	public ResponseEntity<Edificio> edificiobyId(@PathVariable Long id) {
		Edificio edificio = edService.findEdificioById(id);
		ResponseEntity<Edificio> resp = new ResponseEntity<Edificio>(edificio, HttpStatus.OK);
		return resp;
	}
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> createEdificio(@RequestBody Edificio e) {
		
		return new ResponseEntity<String>(edService.saveEdificioAndIndirizzo(e), HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEdificio(@PathVariable Long id) {		
		return new ResponseEntity<String>(edService.removeEdificioById(id), HttpStatus.OK);
	}
	
	
}
