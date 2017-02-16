package es.uniovi.asw.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import es.uniovi.asw.log.LogManager;

public class ReadExcel extends RList{

	@Override
	public List<CitizenInfo> read(String path) {
		List<CitizenInfo> info = new ArrayList<CitizenInfo>();
		LogManager log=new LogManager();

		try {
			FileInputStream file = new FileInputStream(new File(path));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);

			//Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				
				//if there is any cell missing created with null values
				CitizenInfo citizen = new CitizenInfo(
						row.getCell(0) != null ? row.getCell(0).toString() : null, //name
						row.getCell(0) != null ? row.getCell(1).toString() : null, //lastname
						row.getCell(0) != null ? row.getCell(2).toString() : null, //birthday
						row.getCell(0) != null ? row.getCell(3).toString() : null, //email
						row.getCell(0) != null ? row.getCell(4).toString() : null, //NIF
						row.getCell(0) != null ? row.getCell(5).toString() : null, //address
						row.getCell(0) != null ? row.getCell(6).toString() : null, //nationality
						row.getCell(0) != null ? row.getCell(7).toString() : null);//pollingstationcode				
				info.add(citizen);
			}
			
			file.close();		
		}
		catch (FileNotFoundException e) {
			log.addToLog("The excel table wasn't found");
			e.printStackTrace();
		} catch (IOException e) {
			log.addToLog("Exception while reading the excel table");
			e.printStackTrace();			
		}
		return info;
	}

}
