package com.Test.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Test.back.model.Vinarija;

@Repository
public interface VinarijaRepository extends JpaRepository<Vinarija, Long>{
	
	Vinarija findOneById(Long id);
}
