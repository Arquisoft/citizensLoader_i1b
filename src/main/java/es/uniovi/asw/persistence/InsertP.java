package es.uniovi.asw.persistence;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import es.uniovi.asw.log.LogManager;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.parser.CitizenInfo;
import es.uniovi.asw.parser.Parser;
import es.uniovi.asw.password.*;

public class InsertP implements Insert{
	
	LogManager logm=new LogManager();

	@Override
	public List<Citizen> insert(List<CitizenInfo> citizenValues) {
		// TODO Auto-generated method stub
		List<Citizen> citizens = new ArrayList<Citizen>();
		Citizen citizen;
		
		citizens=logm.CheckRepetition(citizens);
		// Inserta y verifica en la base de datos
		for (CitizenInfo v : citizenValues) {
			//se procesa y transforma la fecha
			Date date= new Date();
			try{
				DateFormat format= new SimpleDateFormat("dd/MM/yyyy");
				date= format.parse(v.getBirthday());
			}
			catch (ParseException e) {
				logm.addToLog("Error while trying to apply a format to the date");
			}			
			
		
			citizen = new Citizen(v.getFirstName(),v.getLastName(),date, v.getEmail(), v.getNIF(),v.getAddress(),v.getNationality(),
					Integer.parseInt((v.getPollingStationCode().replace(".0", ""))));
			PasswordGenerator.generatePasswords(citizen);
			
			if(logm.CheckRepetitionUser(citizen, citizens))
			{
				//choose to add the data or not(yes->update citizen,no->log again)
			}

			try {
				Parser.voterRepository.save(citizen);
				citizens.add(citizen);
			} catch (DataIntegrityViolationException e) {
				citizen = Parser.voterRepository.findByEmail(citizen.getEmail());
				citizens.add(citizen);

			}
		}

		System.out.println("Se han registrado " + citizens.size());

		// Devuelve los votantes insertados 
		return citizens;
	}



}
