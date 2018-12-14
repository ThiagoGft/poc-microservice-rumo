package br.com.rumo.poc.delivery.domain.command;

import java.util.UUID;

import br.com.rumo.poc.sharedkernel.command.Command;
import lombok.Data;

@Data
public class ConfirmDeliveryCommand implements Command {
	
	private final UUID idDelivery;

}
