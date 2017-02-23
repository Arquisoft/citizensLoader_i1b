

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.log.LogManager;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.parser.CitizenInfo;

public class testLogger {
	
	private LogManager logm ;
	
	 private List<Citizen> citizens;
	
	@Before
	public void setUp() throws Exception
	{
		
		Citizen citizen = new Citizen("Gabriel","García",new Date(), "email@test.com","55433455B", "cc","cc", 1);
		citizen.setUnhashedPassword("test");
		citizens =new ArrayList<Citizen>();
		citizens.add(citizen);
		logm=new LogManager();
	}

//	@Test
	/*public void CheckDBtest() 
	{
		Citizen citizen = new Citizen("Gabriell","Reguero",new Date(), "email@test.com","55433455B", "dd","dd", 2);
		citizen.setUnhashedPassword("test2");
	
		assertEquals(true,logm.checkRepetitionUser(citizen, citizens));
		
	}*/
	
	@Test
	public void RepeatedDatatest() 
	{
		Citizen citizen = new Citizen("Gabriel","García",new Date(), "email@test.com","55433455B", "cc","cc", 1);
		citizen.setUnhashedPassword("test");
	
		assertEquals(true,logm.checkRepetitionUser(citizen, citizens));
		
	}

	@Test
	public void MissingNametest() 
	{
		CitizenInfo citizen = new CitizenInfo(null,"García",new Date().toString(), "email@test.com","55433455B", "cc","cc", Integer.toString(1));
		
		assertEquals(true,!logm.checkData(citizen));
		
	}
	
	@Test
	public void MissingSurNametest() 
	{
		CitizenInfo citizen = new CitizenInfo("Gabriel",null,new Date().toString(), "email@test.com","55433455B", "cc","cc", Integer.toString(1));
		assertEquals(true,!logm.checkData(citizen));
		
	}
	
	@Test
	public void MissingDatetest() 
	{
		CitizenInfo citizen = new CitizenInfo("Gabriel","García",null, "email@test.com","55433455B", "cc","cc", Integer.toString(1));
		assertEquals(true,!logm.checkData(citizen));
		
	}
	
	@Test
	public void MissingEmailtest() 
	{
		CitizenInfo citizen = new CitizenInfo("Gabriel","García",new Date().toString(), null,"55433455B", "cc","cc", Integer.toString(1));
		
		assertEquals(true,!logm.checkData(citizen));
		
	}
	
	@Test
	public void MissingNIFtest() 
	{
		CitizenInfo citizen = new CitizenInfo("Gabriel","García",new Date().toString(), "email@test.com",null, "cc","cc", Integer.toString(1));
		
		assertEquals(true,!logm.checkData(citizen));
		
	}
	
	@Test
	public void MissingAddresstest() 
	{
		CitizenInfo citizen = new CitizenInfo("Gabriel","García",new Date().toString(), "email@test.com","55433455B", null,"cc", Integer.toString(1));
		
		assertEquals(true,!logm.checkData(citizen));
		
	}
	
	@Test
	public void MissingNationalitytest() 
	{
		CitizenInfo citizen = new CitizenInfo("Gabriel","García",new Date().toString(), "email@test.com","55433455B", "cc",null, Integer.toString(1));
		
		assertEquals(true,!logm.checkData(citizen));
		
	}
	
	@Test
	public void MissingStationCodetest() 
	{
		CitizenInfo citizen = new CitizenInfo("Gabriel","García",new Date().toString(), "email@test.com","55433455B", "cc","cc", null);
		
		assertEquals(true,!logm.checkData(citizen));
		
	}
	
	

}
