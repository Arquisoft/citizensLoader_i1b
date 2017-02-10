package es.uniovi.asw;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Main application
 * 
 * @author Labra
 *
 */
@SpringBootApplication
public class LoadUsers {

	public static void main(String... args) {
		final LoadUsers runner = new LoadUsers();
		runner.run(args);
	}

	// TODO
	@Bean
	void run(String... args) {
		System.out.println("TODO");
	}
}
