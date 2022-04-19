package com.Test.back.service;

import org.springframework.data.domain.Page;

import com.Test.back.model.Vino;

public interface VinoService {
	
	Page<Vino> findAll(int page);
	
	Page<Vino> find(String ime, Long vinarijaId, Integer pageNo);
	
	Vino findOne(Long id);
	
	Vino save(Vino vino);
	
	Vino update(Vino vino);
	
	Vino delete(Long id);
}
