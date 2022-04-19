package com.Test.back.service;

import java.util.List;

import com.Test.back.model.Vinarija;

public interface VinarijaService {
	
	List<Vinarija> findAll();
	
	Vinarija findOne(Long id);
}
