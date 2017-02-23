import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import antlr.collections.List;
import es.uniovi.asw.model.Citizen;

public class testLogger {
	
	 private List<Citizen> citizens;
	
	@Before
	public void setUp() throws Exception
	{
		
		Citizen citizen = new Citizen("Gabriel","García",new Date(), "email@test.com","55433455B", "cc","cc", 1);
		citizen.setUnhashedPassword("test");
		citizens =new ArrayList<Citizen>();
		citizens.add(citizen);
	}


	@Test
	public void MissingNametest() 
	{
		Citizen citizen = new Citizen("Gabriel","García",new Date(), "email@test.com","55433455B", "cc","cc", 1);
		citizen.setUnhashedPassword("test");
		citizens.add(citizen);
		assertEquals();
	}

}
