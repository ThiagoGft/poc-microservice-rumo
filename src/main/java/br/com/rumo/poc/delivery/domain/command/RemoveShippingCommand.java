package br.com.rumo.poc.delivery.domain.command;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RemoveShippingCommand {
	
	private final UUID idDelivery;
	private final UUID idShipping;

}
