package br.com.rumo.poc.sharedkernel.command;

public class CommandHandlerException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -908925162167711834L;

	public CommandHandlerException(){
		super("Parameter Command not found");
	}

}
