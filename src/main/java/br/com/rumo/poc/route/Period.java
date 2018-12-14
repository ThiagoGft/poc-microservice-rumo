package br.com.rumo.poc.route;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Period {
	
	private final LocalDateTime start;
	private final LocalDateTime end;

}
