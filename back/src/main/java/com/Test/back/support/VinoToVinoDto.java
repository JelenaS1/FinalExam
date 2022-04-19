package com.Test.back.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.Test.back.model.Vino;
import com.Test.back.web.dto.VinoDTO;

@Component
public class VinoToVinoDto implements Converter<Vino, VinoDTO>{
	
	@Override
    public VinoDTO convert(Vino vino) {
		VinoDTO vinoDTO = new VinoDTO();
		vinoDTO.setId(vino.getId());
		vinoDTO.setIme(vino.getIme());
		vinoDTO.setOpis(vino.getOpis());
		vinoDTO.setGodinaProizvodnje(vino.getGodinaProizvodnje());
		vinoDTO.setCena(vino.getCena());
		vinoDTO.setBrojFlasa(vino.getBrojFlasa());
		vinoDTO.setTipId(vino.getTip().getId());
		vinoDTO.setTipIme(vino.getTip().getIme());
		
		if(vino.getVinarija() == null) {
			vinoDTO.setVinarijaId(-1L);
			vinoDTO.setVinarijaIme("");
		}else {
			vinoDTO.setVinarijaId(vino.getVinarija().getId());
			vinoDTO.setVinarijaIme(vino.getVinarija().getIme());
		}

        return vinoDTO;
    }

    public List<VinoDTO> convert(List<Vino> vina){
        List<VinoDTO> vinaDto = new ArrayList<>();

        for(Vino vino : vina) {
        	vinaDto.add(convert(vino));
        }

        return vinaDto;
    }
}
