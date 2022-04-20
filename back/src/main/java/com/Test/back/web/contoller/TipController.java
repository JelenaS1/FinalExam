package com.Test.back.web.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Test.back.model.Tip;
import com.Test.back.service.TipService;
import com.Test.back.support.TipToTipDto;
import com.Test.back.web.dto.TipDTO;

@RestController
@RequestMapping(value = "/api/tipovi", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class TipController {
	
	@Autowired
	private TipService tipService;
	
	@Autowired
	private TipToTipDto toTipDto;
	
	@PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
	@GetMapping
	public ResponseEntity<List<TipDTO>> getAll() {

		List<Tip> tipovi = tipService.findAll();

		if (tipovi.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(toTipDto.convert(tipovi), HttpStatus.OK);
		}
	}
}
