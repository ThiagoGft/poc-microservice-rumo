package br.com.rumo.poc.sharedkernel;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.rumo.poc.sharedkernel.command.CommandStub;

@SpringBootApplication
public class ApplicationTeste {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(ApplicationTeste.class, args).close();
	}

	@Bean
	public CommandLineRunner run() {
		return evt -> {
			System.out.println(" START ");
			CommandStub.print();
			CommandStub.publisher(new CreateTesteCommand("ID"));
		};
	}

}
