package es.uniovi.asw.parser;

import java.util.List;

import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.persistence.Insert;
import es.uniovi.asw.persistence.InsertP;

public class InsertR implements Insert{

	@Override
	public List<Citizen> insert(List<CitizenInfo> citizenValues, String path) {
		// TODO Auto-generated method stub
		return new InsertP().insert(citizenValues, path);
	}

}
