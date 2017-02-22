package es.uniovi.asw.log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.parser.CitizenInfo;
import es.uniovi.asw.parser.Parser;

public class LogManager {


	public void addToLog(String line)
	{
		BufferedWriter writer = null;
		try {

			// Create directory if it doesn't exist
			File logsDir = new File("logs");
			if (!logsDir.exists()) {
				logsDir.mkdir();
			}
			//path to the file
			File logFile = new File("logs/errors.log");

			// Check message
			//System.out.println("Updating log file");

			//add the data
			writer = new BufferedWriter(new FileWriter(logFile,true));
			writer.write(line+"\n");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the writer
				writer.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Checks the the citizen list for an equal to the user and puts the error if there is , in the log
	 * 
	 * @param user
	 * @param citizens
	 * @return true if there is repetition false if there is none
	 */
	public boolean CheckRepetitionUser(Citizen user,List<Citizen> citizens)
	{
		//checking if there is repetition inseide the current file
		for(Citizen citizen:citizens)
		{
			if(user.equals(citizen))
			{
				addToLog(":RepeatedUser: The user "+user.hashCode()+" is already on the database.");
				return true;
			}
		}	
		//if the citizen is not in the file it check if it exists on the db
		return checkOnDB(user);
	}

	/**
	 *  checks if the user is already in the database
	 * @param user
	 * @return
	 */
	private boolean checkOnDB(Citizen user) {

		Citizen repeated = null;

		repeated = Parser.citizenRepository.findByEmail(user.getNif());
		if (repeated == null)
		{
			repeated = Parser.citizenRepository.findByNif(user.getEmail());
		}

		return repeated != null; //true if != null cause is not in the db
	}

	//	/**
	//	 * Updates the user with the new data
	//	 * (finds it calls it and add the data of "user").
	//	 * @param user
	//	 * @param citizens
	//	 * 
	//	 */
	//	public Citizen addNewData(Citizen user, List<Citizen> citizens)
	//	{
	//		
	//		for(Citizen citizen:citizens)
	//		{
	//			if(user.equals(citizen))
	//			{
	//				Citizen renovado=Parser.citizenRepository.findByNif(citizen.getNif());
	//				
	//				if(user.getAddress()!=null)
	//				renovado.setAddress(user.getAddress());
	//				if(user.getBirthday()!=null)
	//				renovado.setBirthday(user.getBirthday());
	//				if(user.getEmail()!=null)
	//				renovado.setEmail(user.getEmail());
	//				if(user.getNationality()!=null)
	//				renovado.setNationality(user.getNationality());
	//				if(user.getPassword()!=null)
	//				renovado.setPassword(user.getPassword());
	//				if(user.getUnhashedPassword()!=null)
	//				renovado.setUnhashedPassword(user.getUnhashedPassword());
	//				
	//				Parser.citizenRepository.save(renovado);
	//				
	//				addToLog("Done :The user was updated with the new data");
	//				return renovado;
	//			}
	//		}
	//		return user;
	//		
	//	}

	/**
	 * 
	 * checks if the data is ok (not nulls) 
	 * @param citizen
	 * @return true if no nulls on the attributes
	 */
	public boolean CheckData(CitizenInfo citizen) {
		String aux = "Citizen: "+citizen.hashCode()+" - ";
		
		boolean result = true;
		
		if(citizen.getFirstName() == null){
			addToLog(":Corrupted Data:"+aux+"unexisting name");
			result = false;
		}
		if(citizen.getNIF() == null){
			addToLog(":Corrupted Data: "+aux+"unexisting NIF");
			result = false;
		}
		if(citizen.getEmail() == null){
			addToLog(":Corrupted Data: "+aux+"unexisting email");
			result = false;
		}
		if(citizen.getLastName() == null){
			addToLog(":Corrupted Data: "+aux+"unexisting lastname");
			result = false;
		}	
		if(citizen.getAddress() == null){
			addToLog(":Corrupted Data: "+aux+"unexisting address");
		}
		if(citizen.getNationality() == null){
			addToLog(":Corrupted Data: "+aux+"unexisting nationality");
			result = false;
		}
		if(citizen.getPollingStationCode() == null){
			addToLog(":Corrupted Data: "+aux+"unexisting polling station code");
			result = false;
		}
		if(citizen.getBirthday() == null)
		{
			addToLog(":Corrupted Data: "+aux+"corrupted date");
			result = false;
		}
		return result;
	}



}
