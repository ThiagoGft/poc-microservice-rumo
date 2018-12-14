package br.com.rumo.poc.sharedkernel;

import org.springframework.stereotype.Component;

import br.com.rumo.poc.sharedkernel.command.CommandHandle;

@Component
public class Teste {
	
	@CommandHandle
	public void create(CreateTesteCommand command) {
		System.out.println("CREATED : " + command.getId());
	}
	

}
