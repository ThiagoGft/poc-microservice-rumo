package br.com.rumo.poc.route;

import java.util.UUID;

import br.com.rumo.poc.route.domain.command.CreateRouteCommand;

public class Route {
	
	private UUID id;
	private Routeway routeway;
	private Period period;
	private RouteStatus status;
//	private Boolean inArrears;
	
	public void create(CreateRouteCommand cmd) {
		Routeway createRouteway = new Routeway(cmd.getOrigin(), cmd.getDestiny());
		Period createPeriod = new Period(cmd.getStart(), cmd.getEnd());
		apply(createRouteway, createPeriod);
	}
	
	private void apply(Routeway routeway, Period period) {
		this.id = UUID.randomUUID();
		this.routeway = routeway;
		this.period = period;
		this.status = RouteStatus.RESERVED;
//		this.inArrears = false;
	}

}
