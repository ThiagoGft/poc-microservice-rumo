package br.com.rumo.poc.delivery.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.rumo.poc.delivery.domain.command.ConfirmDeliveryCommand;
import br.com.rumo.poc.delivery.domain.command.CreateDeliveryCommand;
import br.com.rumo.poc.delivery.infraestruture.data.DeliveryRepository;
import br.com.rumo.poc.sharedkernel.command.CommandHandle;
import br.com.rumo.poc.sharedkernel.eventsource.EventStub;
import br.com.rumo.poc.shipping.domain.command.ShippingUpdateStatusCommand;

@Component
public class DeliveryService {

	@Autowired
	DeliveryRepository deliveryRepository;

	@CommandHandle
	public void handle(CreateDeliveryCommand cmd) {
		DeliveryAggregator delivery = new DeliveryAggregator();
		delivery.createDelivery(cmd);
		deliveryRepository.save(delivery);

		publisherEvents(delivery);
	}

	@CommandHandle
	public void handle(ConfirmDeliveryCommand cmd) {
		DeliveryAggregator delivery = deliveryRepository.findOne(cmd.getIdDelivery());
		delivery.confirmDelivery(cmd);
		deliveryRepository.save(delivery);

		publisherEvents(delivery);
	}

	// @CommandHandle
	// public void handle(AddShippingCommand cmd) {
	// DeliveryAggregator delivery = new DeliveryAggregator();
	// delivery.handle(cmd);
	// shippingRepository.insert(shipping);
	//
	// EventStub.publisher(new ShippingCreated(shipping.getId(),
	// shipping.getVehicle(), shipping.getStatus()));
	// }
	//
	// @CommandHandle
	// public void handle(RemoveShippingCommand cmd) {
	// Shipping shipping = new Shipping();
	// shipping.handle(cmd);
	// shippingRepository.delete(cmd.getIdShipping());
	//
	// //publicar evento
	// }
	//
	@CommandHandle
	public void handle(ShippingUpdateStatusCommand cmd) {
		// Shipping shipping = repository.findOne(cmd.getId());
		// shipping.handle(cmd);
		// repository.save(shipping);
	}

	private void publisherEvents(DeliveryAggregator delivery) {
		delivery.getEventStub().stream().forEach(EventStub::publisher);
	}

}
