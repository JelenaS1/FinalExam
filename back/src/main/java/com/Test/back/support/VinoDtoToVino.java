package com.Test.back.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.Test.back.model.Vino;
import com.Test.back.service.TipService;
import com.Test.back.service.VinarijaService;
import com.Test.back.service.VinoService;
import com.Test.back.web.dto.VinoDTO;

@Component
public class VinoDtoToVino implements Converter<VinoDTO, Vino>{
	
	@Autowired
    private VinoService vinoService;
	
	@Autowired
    private VinarijaService vinarijaService;
	
	@Autowired
    private TipService tipService;
	
	@Override
    public Vino convert(VinoDTO dto) {
		Vino vino;

        if(dto.getId() == null){
        	vino = new Vino();
        }else{
        	vino = vinoService.findOne(dto.getId());
        }

        if(vino != null){
        	vino.setIme(dto.getIme());
        	vino.setOpis(dto.getOpis());
        	vino.setGodinaProizvodnje(dto.getGodinaProizvodnje());
        	vino.setCena(dto.getCena());
        	vino.setBrojFlasa(dto.getBrojFlasa());
        	vino.setVinarija(vinarijaService.findOne(dto.getVinarijaId()));
        	vino.setTip(tipService.findOne(dto.getTipId()));
       
        }
        return vino;
    }
}
