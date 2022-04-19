package com.Test.back.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Test.back.model.Vinarija;
import com.Test.back.repository.VinarijaRepository;
import com.Test.back.service.VinarijaService;

@Service
public class JpaVinarijaService implements VinarijaService{
	
	@Autowired
	private VinarijaRepository vinarijaRepository;
	
	@Override
	public List<Vinarija> findAll() {
		return vinarijaRepository.findAll();
	}

	@Override
	public Vinarija findOne(Long id) {
		return vinarijaRepository.findOneById(id);
	}
	
}
