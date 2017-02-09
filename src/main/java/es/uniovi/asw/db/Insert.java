package es.uniovi.asw.db;

import java.util.List;

public interface Insert {
	
	public List<Citizen> insert(List<CitizenInfo> citizenValues, String path);
	
}
