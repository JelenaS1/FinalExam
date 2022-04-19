package com.Test.back.service;

import java.util.List;

import com.Test.back.model.Tip;

public interface TipService {
	
	List<Tip> findAll();
	
	Tip findOne(Long id);
}
