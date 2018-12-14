package br.com.rumo.poc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import br.com.rumo.poc.sharedkernel.Event;

public abstract class Aggregator {
	
	@Id
	protected UUID id;
	
	@Transient
	private List<Event> eventStub = new ArrayList<>();
	
	protected void registerEvent(Event evt) {
		eventStub.add(evt);
	}
	public List<Event> getEventStub(){
		return this.eventStub;
	}
	
	public UUID getId() {
		return this.id;
	}

}
