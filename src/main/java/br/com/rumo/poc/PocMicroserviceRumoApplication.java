package br.com.rumo.poc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.rumo.poc.delivery.domain.command.CreateDeliveryCommand;
import br.com.rumo.poc.delivery.infraestruture.data.DeliveryQueryClientIdRepository;
import br.com.rumo.poc.delivery.infraestruture.data.DeliveryRepository;
import br.com.rumo.poc.sharedkernel.command.CommandStub;
import ch.qos.logback.classic.net.SyslogAppender;

@SpringBootApplication
public class PocMicroserviceRumoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocMicroserviceRumoApplication.class, args).close();
	}

	@Bean
	public CommandLineRunner run(DeliveryRepository repository, DeliveryQueryClientIdRepository query) {
		return evt -> {
			repository.deleteAll();
			query.deleteAll();
			
			UUID clientId = UUID.randomUUID();
			
			CommandStub.publisher(new CreateDeliveryCommand(
				BigDecimal.valueOf(1000L),
				clientId,
				"FLORIANOPOLIS",
				"CURITIBA",
				LocalDateTime.now(),
				LocalDateTime.now().plusHours(5L)));
			
			CommandStub.publisher(new CreateDeliveryCommand(
					BigDecimal.valueOf(20L),
					clientId,
					"FLORIANOPOLIS",
					"CURITIBA",
					LocalDateTime.now(),
					LocalDateTime.now().plusHours(5L)));
			
			System.out.println(" ::: QUERY :::");
			query.findAll().stream().forEach(System.out::println);
			
		};
	}
}

