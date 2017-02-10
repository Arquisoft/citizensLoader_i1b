package es.uniovi.asw.password;

import java.util.List;
import java.util.Random;

import es.uniovi.asw.model.Citizen;

public class PasswordGenerator {

	private static final String pasworsPossibilities = "ABCDEFGHIJKLMNOPQRSTUVXYZ123456789";

	public static String generatePasswords() {
		// TODO Auto-generated method stub
		String password = "";

		Random randGenerator = new Random();

		for (int i = 0; i < 9; i++) {

			password += pasworsPossibilities.charAt(randGenerator.nextInt(pasworsPossibilities.length()));

		}
		return password;
	}
	public static void generatePasswords(List<Citizen> citizen) {

		for (Citizen cit : citizen) {
			((Citizen) citizen).setPassword(generatePasswords());
		}
	}
	
	public static void generatePasswords(Citizen citizen) {
		citizen.setPassword(generatePasswords());
	}

}
