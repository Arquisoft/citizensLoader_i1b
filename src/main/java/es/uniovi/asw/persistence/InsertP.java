package es.uniovi.asw.persistence;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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
		boolean adelante;
		
		
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
				
			adelante=decide(citizen,citizens);
			
			}
			else
			{
				adelante=true;
			}

			if(adelante)
			{	
				try {
					Parser.citizenRepository.save(citizen);
					citizens.add(citizen);
				} catch (DataIntegrityViolationException e) {
					citizen = Parser.citizenRepository.findByEmail(citizen.getEmail());
					citizens.add(citizen);
				}
			}
		}
		
		citizens=logm.CheckRepetition(citizens);

		System.out.println("Se han registrado " + citizens.size());

		// Devuelve los votantes insertados 
		return citizens;
	}
	
	public boolean decide(Citizen citizen,List<Citizen> citizens)
	{
		
		InputStream stream = System.in;
		Scanner scanner = new Scanner(stream);
		System.out.println("The citizen already exist do you want to overwrite it?(yes/no): ");
		String input = scanner.next();
		scanner.close();
		
		if(input.equalsIgnoreCase("Yes"))
		{
			//Si el citizen ya existe este es reemplazado
			citizen=logm.addNewData(citizen, citizens);
			
			System.out.println("The citizen was updated");
			return true;
		}
		else if(input.equalsIgnoreCase("no"))
		{
			
			System.out.println("The citizen wasn't updated" );
			return false;
		}
		else
		{
			System.out.println("Only yes/no");
			return decide(citizen, citizens);
		}
	}



}
