package br.com.rumo.poc.delivery.domain.command;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.rumo.poc.sharedkernel.command.Command;
import lombok.Data;

@Data
public class AddShippingCommand implements Command{
	
	private final UUID idDelivery;
	private final String origin;
	private final String destiny;
	private final LocalDateTime start;
	private final LocalDateTime end;
	private String vehicle;
	
	private String status;

}
