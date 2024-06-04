package br.com.alura.tabelafipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.tabelafipe.principal.Principal;

@SpringBootApplication
public class TabelafipeApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(TabelafipeApplication.class, args);
		
		Principal principal = new Principal();
		
		principal.menu();
		
	}

}
