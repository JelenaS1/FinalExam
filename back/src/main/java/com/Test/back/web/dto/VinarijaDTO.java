package com.Test.back.web.dto;

public class VinarijaDTO {
	private Long id;
	
	private String ime;
	
	private int godinaOsnivanja;

	public VinarijaDTO() {
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
	
	
}
