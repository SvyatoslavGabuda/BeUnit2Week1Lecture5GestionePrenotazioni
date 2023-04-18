package it.epicode.gp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.epicode.gp.model.Utente;
import it.epicode.gp.service.UtenteService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UserRequestTest {
@LocalServerPort
private int port;
@Autowired
private TestRestTemplate rest;
//@Autowired
//UtenteService uService;
	@Test
	void userTest() {
		ResponseEntity<Utente> res = 
				rest.getForEntity("http:localhost:"+port+"/user/1", Utente.class);
		assertEquals(res.getStatusCode(), HttpStatus.OK);
	}

}
