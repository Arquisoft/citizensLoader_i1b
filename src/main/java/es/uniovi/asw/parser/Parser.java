package es.uniovi.asw.parser;

import java.util.List;

import javax.transaction.NotSupportedException;

import es.uniovi.asw.persistence.Insert;
import es.uniovi.asw.persistence.InsertP;
import es.uniovi.asw.persistence.VoterRepository;

public class Parser {
	public static VoterRepository voterRepository;
	private static ReadCitizens reader;

	//We pass here the inputs in the command line in order to generate different writeformats
	public static void run(String[] args) {
		System.out.println(args[0]);
		String filePath = args[0];
		try {
			createReader(filePath);
			//the command line executing syntax is mode path
			List<CitizenInfo> citizenInfo = reader.readCitizens(filePath); 
			Insert inserter = new InsertR();
			inserter.insert(citizenInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * selects a type of reader depending on the extension of the file (using the path)
	 * @param path
	 * @throws Exception 
	 */
	private static void createReader(String path) throws Exception{
		if(path.endsWith(".xlsx")){
			reader = new ReadExcel();
		}
		else{
			throw new NotSupportedException("Not supported file");
		}
	}
	
	
	
	

}
