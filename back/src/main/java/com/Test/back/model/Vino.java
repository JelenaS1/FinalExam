package com.Test.back.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vina")
public class Vino {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String ime;
	
	@Column
	private String opis;
	
	@Column(nullable = false)
	private int godinaProizvodnje;
	
	@Column
	private double cena;
	
	@Column
	private int brojFlasa;
	
	@ManyToOne
	private Tip tip;
	
	@ManyToOne
	private Vinarija vinarija;

	public Vino() {
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

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getGodinaProizvodnje() {
		return godinaProizvodnje;
	}

	public void setGodinaProizvodnje(int godinaProizvodnje) {
		this.godinaProizvodnje = godinaProizvodnje;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public int getBrojFlasa() {
		return brojFlasa;
	}

	public void setBrojFlasa(int brojFlasa) {
		this.brojFlasa = brojFlasa;
	}

	public Tip getTip() {
		return tip;
	}

	public void setTip(Tip tip) {
		this.tip = tip;
	}

	public Vinarija getVinarija() {
		return vinarija;
	}

	public void setVinarija(Vinarija vinarija) {
		this.vinarija = vinarija;
	}
	
	
	
	
}
