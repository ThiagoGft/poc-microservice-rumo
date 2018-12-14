package br.com.rumo.poc.shipping.domain.event;

import java.util.UUID;

import br.com.rumo.poc.sharedkernel.Event;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShippingRemoved implements Event{
	
	private UUID idShipping;

}
