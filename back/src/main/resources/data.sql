INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');

              
INSERT INTO tipovi (id, ime) VALUES (1, 'Suvo belo');
INSERT INTO tipovi (id, ime) VALUES (2, 'Slatko belo');
INSERT INTO tipovi (id, ime) VALUES (3, 'Suvo crno');
INSERT INTO tipovi (id, ime) VALUES (4, 'Slatko crno');

INSERT INTO vinarije (id, godina_osnivanja, ime) VALUES (1, 1994, 'Kovacevic');
INSERT INTO vinarije (id, godina_osnivanja, ime) VALUES (2, 1997, 'Radovanovic');
INSERT INTO vinarije (id, godina_osnivanja, ime) VALUES (3, 2000, 'Dragic');

INSERT INTO vina (id, broj_flasa, cena, godina_proizvodnje, ime, opis, tip_id, vinarija_id) VALUES (1, 20, 800.00, 2010, 'Vranac', 'vino odlicne arome', 3, 2);
INSERT INTO vina (id, broj_flasa, cena, godina_proizvodnje, ime, opis, tip_id, vinarija_id) VALUES (2, 24, 1800.00, 2012, 'Chardone', 'vino odlicne arome', 1, 1);
INSERT INTO vina (id, broj_flasa, cena, godina_proizvodnje, ime, opis, tip_id, vinarija_id) VALUES (3, 30, 2800.00, 2019, 'Merlot', 'vino pune arome', 2, 3);
INSERT INTO vina (id, broj_flasa, cena, godina_proizvodnje, ime, opis, tip_id, vinarija_id) VALUES (4, 120, 700.00, 2011, 'Krstac', 'vino vocne arome', 4, 3);
INSERT INTO vina (id, broj_flasa, cena, godina_proizvodnje, ime, opis, tip_id, vinarija_id) VALUES (5, 0, 5000.00, 2010, 'Orfelin', 'vino slatkih nota', 4, 1);

