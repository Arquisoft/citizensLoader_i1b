package es.uniovi.asw.parser;

import es.uniovi.asw.persistence.VoterRepository;

public class Parser {
	public static VoterRepository voterRepository;
	private static ReadCitizens reader;

	//We pass here the inputs in the command line in order to generate different writeformats
	public static void run(String[] args) {
		System.out.println(args[0]);
		reader = new ReadExcel();
		reader.readCitizens(args[0]); //the command line executing syntax is mode path
	}
	
	
	
	

}
