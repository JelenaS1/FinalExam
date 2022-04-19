package com.Test.back.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.Test.back.model.Vinarija;
import com.Test.back.web.dto.VinarijaDTO;

@Component
public class VinarijaToVinarijaDto implements Converter<Vinarija, VinarijaDTO>{
	
	@Override
	public VinarijaDTO convert(Vinarija vinarija) {
		VinarijaDTO dto = new VinarijaDTO();
		dto.setId(vinarija.getId());
		dto.setIme(vinarija.getIme());
		dto.setGodinaOsnivanja(vinarija.getGodinaOsnivanja());
		return dto;
	}
	
	public List<VinarijaDTO> convert(List<Vinarija> vinarije){
		List<VinarijaDTO> vinarijeDto = new ArrayList<>();
		
		for(Vinarija vinarija : vinarije) {
			vinarijeDto.add(convert(vinarija));
		}
		
		return vinarijeDto;
	}
}
