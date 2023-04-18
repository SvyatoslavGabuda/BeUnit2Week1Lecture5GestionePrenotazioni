package it.epicode.gp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.CALLS_REAL_METHODS;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class InfoRequestTest {
@LocalServerPort
private int port;
@Autowired
private TestRestTemplate rest;
	@Test
	void testEndPoint() throws Exception{
		assertThat(this.rest.getForObject("http://localhost:"+port+"/info", 
				String.class)).contains("Info pag");
	}
	

}
