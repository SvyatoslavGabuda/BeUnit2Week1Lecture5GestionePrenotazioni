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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.epicode.gp.enums.TipoPostazione;
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
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<Postazione>> allPostazioniByTipo(@PathVariable TipoPostazione tipo) {
		List<Postazione> pos = posService.findPostazioneByTipo(tipo);
		ResponseEntity<List<Postazione>> resp = new ResponseEntity<List<Postazione>>(pos, HttpStatus.OK);
		return resp;
	}
	@GetMapping("/tipo/pag/{tipo}")
	public ResponseEntity<Page<Postazione>> allPostazioniByTipoPag(@PathVariable TipoPostazione tipo,Pageable pag) {
		Page<Postazione> pos = posService.findPostazioneByTipoPag(tipo,pag);
		ResponseEntity<Page<Postazione>> resp = new ResponseEntity<Page<Postazione>>(pos, HttpStatus.OK);
		return resp;
	}
	@GetMapping("/tipo")
	public ResponseEntity<List<Postazione>> allPostazioniByTipoAndCitta(@RequestParam TipoPostazione tipo,String citta) {
		List<Postazione> pos = posService.findPostazioneByTipoAndCitta(tipo,citta);
		ResponseEntity<List<Postazione>> resp = new ResponseEntity<List<Postazione>>(pos, HttpStatus.OK);
		return resp;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> postPostazione(@RequestBody Postazione p){
		
		try {
			return new ResponseEntity<String>(posService.savePostazione(p),HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePostazione(@PathVariable Long id){
		return new ResponseEntity<String>(posService.removePostazioneById(id),HttpStatus.OK);
	}
	
}
