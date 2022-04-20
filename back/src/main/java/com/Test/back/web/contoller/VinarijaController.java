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

import com.Test.back.model.Vinarija;
import com.Test.back.service.VinarijaService;
import com.Test.back.support.VinarijaToVinarijaDto;
import com.Test.back.web.dto.VinarijaDTO;

@RestController
@RequestMapping(value = "/api/vinarije", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class VinarijaController {
	
	@Autowired
	private VinarijaService vinarijaService;
	
	@Autowired
	private VinarijaToVinarijaDto toVinarijaDto;
	
	@PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
	@GetMapping
	public ResponseEntity<List<VinarijaDTO>> getAll() {

		List<Vinarija> vinarije = vinarijaService.findAll();

		if (vinarije.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(toVinarijaDto.convert(vinarije), HttpStatus.OK);
		}
	}
}
