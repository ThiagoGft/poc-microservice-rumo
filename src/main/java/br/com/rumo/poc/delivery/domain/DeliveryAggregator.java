package br.com.rumo.poc.delivery.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.rumo.poc.Aggregator;
import br.com.rumo.poc.delivery.domain.command.AddShippingCommand;
import br.com.rumo.poc.delivery.domain.command.ConfirmDeliveryCommand;
import br.com.rumo.poc.delivery.domain.command.CreateDeliveryCommand;
import br.com.rumo.poc.delivery.domain.command.RemoveShippingCommand;
import br.com.rumo.poc.delivery.domain.event.DeliveryConfirmed;
import br.com.rumo.poc.delivery.domain.event.DeliveryCreated;
import br.com.rumo.poc.delivery.domain.event.ShippingRemoved;
import br.com.rumo.poc.route.Route;
import br.com.rumo.poc.route.domain.command.CreateRouteCommand;
import br.com.rumo.poc.shipping.domain.Shipping;
import br.com.rumo.poc.shipping.domain.command.CreateShippingCommand;
import lombok.Getter;

@Getter
public class DeliveryAggregator extends Aggregator{

	private DeliveryStatus status;
	private Route route;
	private BigDecimal price;
	private UUID clientId;
	private List<Shipping> itineration;
	private LocalDateTime confirmationDate;

	public void createDelivery(CreateDeliveryCommand cmd) {
		Route createRoute = new Route();
		createRoute.create(new CreateRouteCommand(cmd.getOrigin(), cmd.getDestiny(), cmd.getStart(), cmd.getEnd()));				
		this.apply(createRoute, cmd.getPrice(), cmd.getClientId());
		this.registerEvent(new DeliveryCreated(this.getId(), this.getClientId(), this.getPrice(), this.getRoute(), this.getStatus()));
	}
	
	public void confirmDelivery(ConfirmDeliveryCommand cmd) {
		this.apply(cmd);
		this.registerEvent(new DeliveryConfirmed(this.id, this.clientId, this.confirmationDate, this.price));
	}

	public void addShipping(AddShippingCommand cmd) {
		Shipping shipping = new Shipping();
		shipping.createShipping(new CreateShippingCommand(UUID.randomUUID(), cmd.getOrigin(), cmd.getDestiny(), cmd.getStart(), cmd.getEnd(), cmd.getVehicle()));
		this.itineration.add(shipping);
	}
	
	public void removeShipping(RemoveShippingCommand cmd) {
		for (int i = 0; i < this.itineration.size(); i++) {
			if(this.itineration.get(i).getId().equals(cmd.getIdShipping())){
				this.itineration.remove(i);
				this.registerEvent(new ShippingRemoved(cmd.getIdDelivery(), cmd.getIdShipping()));
				return;
			}
		}	
	}

	private void apply(ConfirmDeliveryCommand confirmDeliveryCommand) {
		this.confirmationDate = LocalDateTime.now();
		this.status = DeliveryStatus.CONFIRMED;
	}

	public void apply(Route route, BigDecimal price, UUID clientId) {
		this.id = UUID.randomUUID();
		this.status = DeliveryStatus.DRAFT;
		this.route = route;
		this.price = price;
		this.clientId = clientId;
		this.itineration = new ArrayList<>();
	}

}
