package br.com.rumo.poc.delivery.domain.command;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.rumo.poc.sharedkernel.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateDeliveryCommand implements Command{
	private final BigDecimal price;
	private final UUID clientId;
	private final String origin;
	private final String destiny;
	private LocalDateTime start;
	private LocalDateTime end;
}
