package br.com.rumo.poc.delivery.domain.event;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.rumo.poc.delivery.domain.DeliveryStatus;
import br.com.rumo.poc.route.Route;
import br.com.rumo.poc.sharedkernel.Event;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeliveryCreated implements Event {
	
	private final UUID id;
	private final UUID clientId;
	private final BigDecimal price;
	private final Route route;
	private final DeliveryStatus status;

}
