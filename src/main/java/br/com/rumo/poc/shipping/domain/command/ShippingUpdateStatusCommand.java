package br.com.rumo.poc.shipping.domain.command;

import java.util.UUID;

import br.com.rumo.poc.sharedkernel.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShippingUpdateStatusCommand implements Command {

	private UUID id;
	private String status;

}
