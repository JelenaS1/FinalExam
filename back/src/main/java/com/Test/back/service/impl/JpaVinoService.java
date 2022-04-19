package com.Test.back.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.Test.back.model.Vino;
import com.Test.back.repository.VinoRepository;
import com.Test.back.service.VinoService;

@Service
public class JpaVinoService implements VinoService{
	
	@Autowired
	private VinoRepository vinoRepository;
	
	@Override
	public Page<Vino> findAll(int page) {
		return vinoRepository.findAll(PageRequest.of(page, 3));
	}

	@Override
	public Page<Vino> find(String ime, Long vinarijaId, Integer pageNo) {
		
		if (ime == null) {
			ime = "";
        }
		
		if(vinarijaId == null) {
			return vinoRepository.findByImeIgnoreCaseContains(ime, PageRequest.of(pageNo, 3));
		}
		return vinoRepository.findByImeIgnoreCaseContainsAndVinarijaId(ime, vinarijaId, PageRequest.of(pageNo, 3));
	}

	@Override
	public Vino findOne(Long id) {
		return vinoRepository.findOneById(id);
	}

	@Override
	public Vino save(Vino vino) {
		return vinoRepository.save(vino);
	}

	@Override
	public Vino update(Vino vino) {
		return vinoRepository.save(vino);
	}

	@Override
	public Vino delete(Long id) {
		Vino vino = findOne(id);
		if(vino != null) {
			vino.getTip().getVina().remove(vino);
			if(vino.getVinarija() != null) {
				vino.getVinarija().getVina().remove(vino);
			}
			vinoRepository.delete(vino);
		
			return vino;
		}
		return null;
	}

}
