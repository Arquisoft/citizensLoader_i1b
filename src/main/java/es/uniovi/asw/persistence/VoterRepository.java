package es.uniovi.asw.persistence;

import org.springframework.data.repository.CrudRepository;

import es.uniovi.asw.model.Citizen;

public interface VoterRepository extends CrudRepository<Citizen, Long> {

	Citizen findByEmail(String email);
	Citizen findByNif(String nif);
}
