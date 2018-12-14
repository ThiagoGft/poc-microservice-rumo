package br.com.rumo.poc.route.domain.command;

import java.time.LocalDateTime;

import br.com.rumo.poc.sharedkernel.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateRouteCommand implements Command{

	private final String origin;
	private final String destiny;
	private final LocalDateTime start;
	private final LocalDateTime end;

}
