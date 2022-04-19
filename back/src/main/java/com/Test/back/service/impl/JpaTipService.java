package com.Test.back.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Test.back.model.Tip;
import com.Test.back.repository.TipRepository;
import com.Test.back.service.TipService;

@Service
public class JpaTipService implements TipService{
	
	@Autowired
	private TipRepository tipRepository;
	
	@Override
	public List<Tip> findAll() {
		return tipRepository.findAll();
	}

	@Override
	public Tip findOne(Long id) {
		return tipRepository.findOneById(id);
	}

}
