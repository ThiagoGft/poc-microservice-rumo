package br.com.rumo.poc.sharedkernel.eventsource;

public class EventHandlerException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1220818766293742370L;

	public EventHandlerException(){
		super("Parameter Event not found");
	}

}
