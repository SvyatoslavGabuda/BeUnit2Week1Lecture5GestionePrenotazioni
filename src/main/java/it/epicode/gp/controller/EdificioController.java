package it.epicode.gp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.epicode.gp.model.Edificio;

import it.epicode.gp.service.EdificioService;

@Controller
@RequestMapping("/edificio")
public class EdificioController {
	@Autowired
	EdificioService edService;
	@GetMapping
	public ResponseEntity<List<Edificio>> allUtenti() {
		List<Edificio> listaUtenti = edService.findAllEdificio();
		ResponseEntity<List<Edificio>> resp = new ResponseEntity<List<Edificio>>(listaUtenti, HttpStatus.OK);
		return resp;
	}
}
