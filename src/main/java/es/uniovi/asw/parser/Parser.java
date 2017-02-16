package es.uniovi.asw.parser;

import java.util.List;

import javax.transaction.NotSupportedException;

import org.springframework.beans.factory.annotation.Autowired;

import es.uniovi.asw.persistence.Insert;
import es.uniovi.asw.persistence.InsertP;
import es.uniovi.asw.persistence.CitizenRepository;

public class Parser {
	public static CitizenRepository citizenRepository;
	private static ReadCitizens reader;

	//We pass here the inputs in the command line in order to generate different writeformats
	@Autowired
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
