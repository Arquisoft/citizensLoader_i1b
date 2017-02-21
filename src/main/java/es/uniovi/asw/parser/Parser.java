package es.uniovi.asw.parser;

import java.io.IOException;
import java.util.List;

import javax.transaction.NotSupportedException;

import org.springframework.beans.factory.annotation.Autowired;

import es.uniovi.asw.persistence.Insert;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.persistence.CitizenRepository;

public class Parser {
	private final static String PDF_COMMAND="p";
	private final static String TXT_COMMAND="t";
	private final static String DOCX_COMMAND="d";
	public static CitizenRepository citizenRepository;
	private static ReadCitizens reader;
	private static LetterGen letterGen = new LetterGenPdf();


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
			List<Citizen> letCit = inserter.insert(citizenInfo);
			// Generate the letters
			generateLetter(letCit, args);
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

	private static void generateLetter(List<Citizen> letCit, String... input) throws IOException{
		for(String i: input){
			switch (i){
			case PDF_COMMAND:
				letterGen = new LetterGenPdf();
				letterGen.generateLetters(letCit); 
			case TXT_COMMAND:
				letterGen = new LetterGenTxt();
				letterGen.generateLetters(letCit); 
			case DOCX_COMMAND:
				letterGen = new LetterGenDocx();
				letterGen.generateLetters(letCit); 
			}
		}

	}





}
