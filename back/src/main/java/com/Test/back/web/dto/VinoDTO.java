package com.Test.back.web.dto;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class VinoDTO {
	
	private Long id;
	
	private String ime;
	
	@Size(max = 120)
	private String opis;
	
	@Positive
	private int godinaProizvodnje;
	
	@Positive
	private double cena;
	
	private int brojFlasa;
	
	private Long tipId;
	
	private String tipIme;
	
	private Long vinarijaId;
	
	private String vinarijaIme;

	public VinoDTO() {
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

	public Long getTipId() {
		return tipId;
	}

	public void setTipId(Long tipId) {
		this.tipId = tipId;
	}

	public String getTipIme() {
		return tipIme;
	}

	public void setTipIme(String tipIme) {
		this.tipIme = tipIme;
	}

	public Long getVinarijaId() {
		return vinarijaId;
	}

	public void setVinarijaId(Long vinarijaId) {
		this.vinarijaId = vinarijaId;
	}

	public String getVinarijaIme() {
		return vinarijaIme;
	}

	public void setVinarijaIme(String vinarijaIme) {
		this.vinarijaIme = vinarijaIme;
	}
	

	
	
}
