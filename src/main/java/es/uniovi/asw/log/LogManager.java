package es.uniovi.asw.log;
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


	/*public boolean CheckRepetitionUser(User user)
	{
		int count;
		
		
	}*/
	

	private void citizenRepeated(Citizen c) {
		
		String line = "The citizen: "+c.toString()+" was found repeated when loading the data";
		
	}

}
