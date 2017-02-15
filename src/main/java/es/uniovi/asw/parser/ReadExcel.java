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

public class ReadExcel extends Reader{

	@Override
	public List<CitizenInfo> read(String path) {
		List<CitizenInfo> info = new ArrayList<CitizenInfo>();

		try {
			FileInputStream file = new FileInputStream(new File(path));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);

			//Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				
				CitizenInfo citizen = new CitizenInfo(
						row.getCell(0).toString() , //name
						row.getCell(1).toString(), //lastname
						row.getCell(2).toString(), //birthday
						row.getCell(3).toString(), //email
						row.getCell(4).toString(), //NIF
						row.getCell(5).toString(), //address
						row.getCell(6).toString(), //nationality
						row.getCell(7).toString());//pollingstationcode				
				info.add(citizen);
			}
			
			file.close();		
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();			
		}
		return info;
	}

}
