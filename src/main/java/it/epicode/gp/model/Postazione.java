package it.epicode.gp.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import it.epicode.gp.enums.StatoPostazione;
import it.epicode.gp.enums.TipoPostazione;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToOne;
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
@Table(name = "postazioni")
public class Postazione {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_postazione;
	private String descrizione;
	@Enumerated(EnumType.STRING)
	private TipoPostazione tipoPostazione;
	@Enumerated(EnumType.STRING)
	private StatoPostazione statoPostazione;
	private int nMaxOccupanti;
	@JsonBackReference(value ="edificio_postazione")
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Edificio edificio;
	@JsonIgnoreProperties("postazione")
	//@JsonManagedReference(value = "postazione_prenotazione")
	@OneToMany(mappedBy = "postazione", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<Prenotazione> prenotazione;
	

}
