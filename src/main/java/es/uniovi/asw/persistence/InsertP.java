package es.uniovi.asw.persistence;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.parser.CitizenInfo;
import es.uniovi.asw.parser.Parser;
import es.uniovi.asw.password.*;

public class InsertP implements Insert{

	@Override
	public List<Citizen> insert(List<CitizenInfo> citizenValues, String path) {
		// TODO Auto-generated method stub
		List<Citizen> citizens = new ArrayList<Citizen>();
		Citizen citizen;
		// Inserta y verifica en la base de datos
		for (CitizenInfo v : citizenValues) {
		Date date= new Date();
			try{
				DateFormat format= new SimpleDateFormat("dd/MM/yyyy");
				date= format.parse(v.getBirthday());
			}
			catch (ParseException e) {
				// TODO: handle exception
			}
			
			
				citizen = new Citizen(v.getFirstName(),v.getLastName(),date, v.getEmail(), v.getNIF(),v.getAddress(),v.getNationality(),
						Integer.parseInt((v.getPollingStationCode().replace(".0", ""))));
				PasswordGenerator.generatePasswords(citizen);
				
				try {
					Parser.voterRepository.save(citizen);
					citizens.add(citizen);
				} catch (DataIntegrityViolationException e) {
					citizen = Parser.voterRepository.findByEmail(citizen.getEmail());
					citizens.add(citizen);
				
			}
		}
		
		System.out.println(citizenValues.size() + " filas leídas del fichero");
		
		if (citizenValues.size() > citizens.size())
			System.out.println((citizenValues.size() - citizens.size()) + " filas con errores en el fichero (ver fails.log para más información)");
		
		System.out.println("Se han registrado " + citizens.size() + " votantes y generado las cartas personales");
		
		// Devuelve los votantes insertados
		return citizens;
	}

	

}
