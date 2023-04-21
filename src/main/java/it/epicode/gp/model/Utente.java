package it.epicode.gp.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
	@Column(nullable = false, unique = true)
	private String username;
	private String name;
	private String lastname;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String password;

	// @JsonIgnore
	// @JsonManagedReference(value = "utente_prenotazione")
	@JsonIgnoreProperties("utente")
	@OneToMany(mappedBy = "utente", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Prenotazione> prenotazioni;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	//@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Set<Role> roles = new HashSet<>();

	public Utente(String username, String name, String lastname, String email) {
		super();
		this.username = username;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
	}
}
