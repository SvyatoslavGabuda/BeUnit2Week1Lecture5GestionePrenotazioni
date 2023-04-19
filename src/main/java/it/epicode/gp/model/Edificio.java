package it.epicode.gp.model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "edifici")
public class Edificio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_edificio;
private String nome;
@JsonManagedReference(value = "edificio_indirizzo")
@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
private Indirizzo indirizzo;
//@JsonIgnoreProperties()
@JsonManagedReference(value ="edificio_postazione")
@OneToMany(mappedBy = "edificio",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
private List<Postazione> postazioni;
}
