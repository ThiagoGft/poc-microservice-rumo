package br.com.rumo.poc.shipping.domain.event;

import br.com.rumo.poc.sharedkernel.Event;
import br.com.rumo.poc.shipping.domain.Shipping;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShippingStatusUpdated implements Event{

	private Shipping shipping;
	
}
