package com.Test.back.web.contoller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Test.back.model.Vino;
import com.Test.back.service.VinoService;
import com.Test.back.support.VinoDtoToVino;
import com.Test.back.support.VinoToVinoDto;
import com.Test.back.web.dto.VinoDTO;

@RestController
@RequestMapping(value = "/api/vina", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class VinoController {
	
	@Autowired
	private VinoService vinoService;
	
	@Autowired
	private VinoDtoToVino toVino;
	
	@Autowired
	private VinoToVinoDto toVinoDto;
	
//	@PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
	@GetMapping
	public ResponseEntity<List<VinoDTO>> getAll( 
			@RequestParam(required = false) String ime,
			@RequestParam(required = false) Long vinarijaId,
			@RequestParam(value = "pageNo", defaultValue = "0") int pageNo){
		
		Page<Vino> page;
		
		if (ime != null || vinarijaId != null) {
			page = vinoService.find(ime, vinarijaId, pageNo);
		} else {
			page = vinoService.findAll(pageNo);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Total-Pages", Integer.toString(page.getTotalPages()));
		headers.add("Access-Control-Expose-Headers", "*");
		System.out.println(page.getTotalPages());
		
		return new ResponseEntity<>(toVinoDto.convert(page.getContent()), headers, HttpStatus.OK);
	}
	
//	@PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<VinoDTO> getOne(@PathVariable Long id){
		Vino vino = vinoService.findOne(id);
		
		if(vino != null) {
			return new ResponseEntity<>(toVinoDto.convert(vino), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
//	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VinoDTO> create(@Valid @RequestBody VinoDTO vinoDTO){
		Vino vino = toVino.convert(vinoDTO);
		vino.setBrojFlasa(0);
		
		Vino sacuvanoVino = vinoService.save(vino);
	
		return new ResponseEntity<>(toVinoDto.convert(sacuvanoVino),  HttpStatus.CREATED);
	}
	
//	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VinoDTO> update(@PathVariable Long id, @Valid @RequestBody VinoDTO vinoDTO){
		
		if(!id.equals(vinoDTO.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Vino vino = toVino.convert(vinoDTO);
		vino.setBrojFlasa(0);
		Vino sacuvanoVino = vinoService.update(vino);
		
		return new ResponseEntity<>(toVinoDto.convert(sacuvanoVino), HttpStatus.OK);
	}
	
//	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		Vino obrisanoVino = vinoService.delete(id);
		if(obrisanoVino != null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
//	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/naruci/{id}")
	public ResponseEntity<VinoDTO> nabavi(@PathVariable Long id, @RequestParam Integer kolicinaS){
		Vino vino = vinoService.findOne(id);
		
		int stanje = vino.getBrojFlasa();
		
		int novaKolicina = kolicinaS + stanje;
		vino.setBrojFlasa(novaKolicina);
		
		vinoService.update(vino);
		
		return new ResponseEntity<>(toVinoDto.convert(vino), HttpStatus.OK);
	}
	
//	@PreAuthorize("hasAnyRole('ROLE_KORISNIK')")
	@GetMapping("/kupi/{id}")
	public ResponseEntity<VinoDTO> kupi(@PathVariable Long id, @RequestParam Integer kolicinaS){
		Vino vino = vinoService.findOne(id);
		
		int stanje = vino.getBrojFlasa();
		if(stanje >= kolicinaS) {
			int novaKolicina = stanje - kolicinaS;
			vino.setBrojFlasa(novaKolicina);
			
			vinoService.update(vino);
			return new ResponseEntity<>(toVinoDto.convert(vino), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
