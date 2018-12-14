package br.com.rumo.poc.delivery.domain.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.rumo.poc.sharedkernel.Event;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeliveryConfirmed implements Event {
	
	private final UUID id;
	private final UUID clientId;
	private final LocalDateTime confirmationDate;
	private final BigDecimal price;

}
