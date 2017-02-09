package es.uniovi.asw.persistence;

import java.util.List;

import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.parser.CitizenInfo;

public interface Insert {
	
	public List<Citizen> insert(List<CitizenInfo> citizenValues, String path);
	
}
