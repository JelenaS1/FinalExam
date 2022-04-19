package com.Test.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Test.back.model.Korisnik;


@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

    Optional<Korisnik> findFirstByKorisnickoIme(String korisnickoIme);

    Optional<Korisnik> findFirstByKorisnickoImeAndLozinka(String korisnickoIme,String lozinka);
}
