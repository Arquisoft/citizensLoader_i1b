package es.uniovi.asw.log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import es.uniovi.asw.model.Citizen;

public class LogManager {
	
	
	public List<Citizen> CheckRepetition(List<Citizen> citizens)
	{
		int count;
		for(Citizen c: citizens)
		{
			count=0;
			
			for(Citizen ci: citizens)
			{
				if(ci.equals(c))
				{
					count++;
				}
			}
			while(count>1)
			{
				for(int i=0;i<count-1;i++)
				{
					count--;
					citizenRepeated(c);
					citizens.remove(c);
				}
			
			}
		}
		return citizens;
		
	}

 public void addToLog(String line)
 {
	 BufferedWriter writer = null;
     try {
         //path to the file
    	 File logFile = new File("main/files/log.txt");

         // Check message
         System.out.println("Updating log file");

         //add the data
         writer = new BufferedWriter(new FileWriter(logFile,true));
         writer.write(line);
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
		for(Citizen citizen:citizens)
		{
			if(user.equals(citizen))
			{
				addToLog("Error :The user is already on the database :RepeatedUser:");
				return true;
			}
		}
		return false;
		
		
	}
 
 	private void resetLogFile()
 	{
 	        FileWriter fw;
			try {
				
			fw = new FileWriter("main/files/log.txt", false);
			
 	        PrintWriter pw = new PrintWriter(fw, false);
 	        pw.flush();
 	        pw.close();
 	        fw.close();
 	   } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
 	}
	

	private void citizenRepeated(Citizen c) {
		
		String line = "The citizen: "+c.toString()+" was found repeated when loading the data";
		addToLog(line);
		
	}

}
