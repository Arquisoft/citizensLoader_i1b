package es.uniovi.asw.parser;

import java.util.List;

public abstract class Reader implements ReadCitizens {

	private Reader reader;
	private String filePath;
	
	@Override
	public List<CitizenInfo> readCitizens(String path) {
		createReader(path);
		return reader.read(filePath);
		
	}
	
	/**
	 * selects a type of reader depending on the extension of the file (using the path)
	 * @param path
	 */
	private void createReader(String path){
		//TODO 
		reader = new ReadExcel();
	}
	
	protected abstract List<CitizenInfo> read(String path);
	
	

}
