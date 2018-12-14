package br.com.rumo.poc.sharedkernel;

import br.com.rumo.poc.sharedkernel.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateTesteCommand implements Command{
	
	private String id;

}
