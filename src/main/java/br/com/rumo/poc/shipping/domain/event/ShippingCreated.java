package br.com.rumo.poc.shipping.domain.event;

import java.util.UUID;

import br.com.rumo.poc.sharedkernel.Event;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShippingCreated implements Event{
		
	private UUID id;
//	private Route route;
	private String vehicle;
	private String status;

}
