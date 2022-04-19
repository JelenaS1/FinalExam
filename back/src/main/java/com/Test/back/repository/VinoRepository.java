package com.Test.back.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Test.back.model.Vino;

@Repository
public interface VinoRepository extends JpaRepository<Vino, Long>{
	
	Vino findOneById(Long id);
	
	Page<Vino> findByImeIgnoreCaseContains(String ime, Pageable pageable);
	
	Page<Vino> findByImeIgnoreCaseContainsAndVinarijaId(String ime, Long vinarijaId, Pageable pageable);
}
