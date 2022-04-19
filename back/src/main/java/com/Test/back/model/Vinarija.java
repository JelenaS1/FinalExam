package com.Test.back.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "vinarije")
public class Vinarija {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String ime;
	
	@Column
	private int godinaOsnivanja;
	
	@OneToMany(mappedBy = "vinarija", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Vino> vina = new ArrayList<>();

	public Vinarija() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public int getGodinaOsnivanja() {
		return godinaOsnivanja;
	}

	public void setGodinaOsnivanja(int godinaOsnivanja) {
		this.godinaOsnivanja = godinaOsnivanja;
	}

	public List<Vino> getVina() {
		return vina;
	}

	public void setVina(List<Vino> vina) {
		this.vina = vina;
	}
	
	
	
	
}
