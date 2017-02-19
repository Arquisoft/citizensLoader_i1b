package es.uniovi.asw.parser;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import es.uniovi.asw.model.Citizen;

public class LetterGenTxt implements LetterGen {

	@Override
	public void generateLetters(List<Citizen> citizens) throws IOException {
		for (Citizen citizen: citizens) {
				PrintWriter writer = new PrintWriter(citizen.getEmail()+".txt", "UTF-8");
				writer.println("Your user has been created.");
				writer.println(citizen.getEmail());
				writer.println(citizen.getUnhashedPassword());
				writer.close();
			}
		}

	}