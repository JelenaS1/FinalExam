package com.Test.back.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.Test.back.model.Tip;
import com.Test.back.web.dto.TipDTO;

@Component
public class TipToTipDto implements Converter<Tip, TipDTO>{
	
	@Override
	public TipDTO convert(Tip tip) {
		TipDTO dto = new TipDTO();
		dto.setId(tip.getId());
		dto.setIme(tip.getIme());
		return dto;
	}
	
	public List<TipDTO> convert(List<Tip> tipovi){
		List<TipDTO> tipoviDto = new ArrayList<>();
		
		for(Tip tip: tipovi) {
			tipoviDto.add(convert(tip));
		}
		
		return tipoviDto;
	}

}
