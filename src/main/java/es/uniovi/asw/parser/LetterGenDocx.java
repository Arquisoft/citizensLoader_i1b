package es.uniovi.asw.parser;

import java.io.IOException;
import java.util.List;

import es.uniovi.asw.model.Citizen;

public class LetterGenDocx implements LetterGen {

	@Override
	public void generateLetters(List<Citizen> citizens) throws IOException {
		for (Citizen citizen: citizens) {			
			XWPFDocument letter = new XWPFDocument();
			XWPFParagraph paragraph = letter.createParagraph();
			XWPFRun textRun = paragraph.createRun();
			textRun.setText(citizen.getEmail());
			textRun.addCarriageReturn();
			textRun.setText(citizen.getUnhashedPassword());
			textRun.setFontSize(12);
			letter.write(new FileOutputStream(new File("letters/"+citizen.getEmail()+".docx")));
			letter.close();
		}
	}

}
