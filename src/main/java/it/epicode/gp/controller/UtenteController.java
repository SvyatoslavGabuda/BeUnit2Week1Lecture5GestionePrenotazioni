package it.epicode.gp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import it.epicode.gp.model.Utente;
import it.epicode.gp.service.UtenteService;

@RestController
@RequestMapping("/user")
public class UtenteController {
	@Autowired
	UtenteService uService;

	@GetMapping
	public ResponseEntity<List<Utente>> allUtenti( ){
		List<Utente> listaUtenti = uService.findAllUtente();
		ResponseEntity<List<Utente>> resp = new ResponseEntity<List<Utente>>(listaUtenti,HttpStatus.OK);
		return resp;
	}
	@GetMapping("/{id}")
	public ResponseEntity<Utente> idUtenti(@PathVariable Long id){
		Utente u = uService.findUtenteById(id);
		ResponseEntity<Utente> resp = new ResponseEntity<Utente>(u,HttpStatus.OK);
		return resp;
	}
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Utente> createUser(@RequestBody Utente u){
		return new ResponseEntity<Utente>(uService.createUtente(u),HttpStatus.CREATED);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id){
		return new ResponseEntity<String>(uService.removeUtenteById(id),HttpStatus.OK);
	}
}
