package it.epicode.gp.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.github.javafaker.Faker;

import it.epicode.gp.model.Edificio;
import it.epicode.gp.model.Indirizzo;

@Configuration
public class EdificioConfig {
	Faker fake = Faker.instance(new Locale("it-It"));

	@Bean("RandomIndirizzo")
	@Scope("prototype")
	public Indirizzo randomIndirizzo() {

		Indirizzo i = new Indirizzo();
		i.setCitta(fake.address().cityName());
		i.setVia(fake.address().streetName());
		i.setN_civico(fake.number().numberBetween(0, 100));
		return i;
	}

	@Bean("RandomEdificio")
	@Scope("prototype")
	public Edificio randomEdificio() {

		Edificio e = new Edificio();
		e.setNome(fake.name().fullName() + " Palace");
		e.setIndirizzo(randomIndirizzo());
		e.setCodice(fake.number().numberBetween(10000, 900000)+"");

		return e;
	}

	@Bean("ParamIndirizzo")
	@Scope("prototype")
	public Indirizzo paramsIndirizzo(String citta,String via,int numero) {

		Indirizzo i = new Indirizzo();
		i.setCitta(citta);
		i.setVia(via);
		i.setN_civico(numero);
		return i;
	}
	@Bean("ParamEdificio")
	@Scope("prototype")
	public Edificio paramEdificio(String name, String citta,String via, int n) {

		Edificio e = new Edificio();
		Indirizzo i = new Indirizzo();
		i.setCitta(citta);
		i.setN_civico(n);
		i.setVia(via);
		e.setNome(name);
		e.setIndirizzo(i);

		return e;
	}

}
