package it.epicode.gp.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "utenti")
public class Utente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_utente;
	private String username;
	private String name;
	private String lastname;
	private String email;
	
	//@JsonIgnore
	@JsonManagedReference(value = "utente_prenotazione")
	//@JsonIgnoreProperties()
	@OneToMany(mappedBy = "utente", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<Prenotazione> prenotazioni;

	public Utente(String username, String name, String lastname, String email) {
		super();
		this.username = username;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
	}
}
