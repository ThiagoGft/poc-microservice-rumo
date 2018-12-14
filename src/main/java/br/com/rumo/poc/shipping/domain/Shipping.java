package br.com.rumo.poc.shipping.domain;

import java.util.UUID;

import org.springframework.data.annotation.Id;

import br.com.rumo.poc.route.Route;
import br.com.rumo.poc.route.domain.command.CreateRouteCommand;
import br.com.rumo.poc.sharedkernel.eventsource.EventStub;
import br.com.rumo.poc.shipping.domain.command.CreateShippingCommand;
import br.com.rumo.poc.shipping.domain.command.ShippingUpdateStatusCommand;
import br.com.rumo.poc.shipping.domain.event.ShippingStatusUpdated;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Shipping {
	
	@Id
	private UUID id;
	private Route route;
	private String vehicle;
	private String status;
	
	public Shipping() {
		super();
	}

	public void createShipping(CreateShippingCommand cmd) {
		Route createRoute = new Route();
		createRoute.create(new CreateRouteCommand(cmd.getOrigin(), cmd.getDestiny(), cmd.getStart(), cmd.getEnd()));
		apply(createRoute, cmd.getVehicle());
	}
	
	public void handle(ShippingUpdateStatusCommand cmd) {
		apply(cmd);
		EventStub.publisher(new ShippingStatusUpdated(this));
	}
	
	private void apply(Route route, String vehicle) {
		this.id = UUID.randomUUID();
		this.route = route;
		this.vehicle = vehicle;
		this.status = "PLANNED";
	}
	
	private void apply(ShippingUpdateStatusCommand cmd) {
		this.status = cmd.getStatus();
	}
	
	

}
