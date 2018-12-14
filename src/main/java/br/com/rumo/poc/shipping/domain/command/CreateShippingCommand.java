package br.com.rumo.poc.shipping.domain.command;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.rumo.poc.sharedkernel.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateShippingCommand implements Command{
	
	private UUID id;
	private final String origin;
	private final String destiny;
	private final LocalDateTime start;
	private final LocalDateTime end;
	private String vehicle;

}
