package br.com.rumo.poc.delivery.domain.event;

import java.util.UUID;

import br.com.rumo.poc.sharedkernel.Event;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShippingRemoved implements Event{
	
	private final UUID idDelivery;
	private final UUID idShippingRemoved;

}
