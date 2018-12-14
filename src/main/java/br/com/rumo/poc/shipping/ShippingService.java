//package br.com.rumo.poc.shipping;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import br.com.rumo.poc.sharedkernel.command.CommandHandle;
//import br.com.rumo.poc.sharedkernel.eventsource.EventHandle;
//import br.com.rumo.poc.sharedkernel.eventsource.EventStub;
//import br.com.rumo.poc.shipping.domain.Shipping;
//import br.com.rumo.poc.shipping.domain.command.CreateShippingCommand;
//import br.com.rumo.poc.shipping.domain.command.RemoveShippingCommand;
//import br.com.rumo.poc.shipping.domain.command.ShippingUpdateStatusCommand;
//import br.com.rumo.poc.shipping.domain.event.ShippingCreated;
//import br.com.rumo.poc.shipping.domain.event.ShippingStatusUpdated;
//
//@Component
//public class ShippingService {
//	
//	@Autowired
//	private ShippingRepository repository;
//	
//	@CommandHandle
//	public void handle(CreateShippingCommand cmd) {	
//		Shipping shipping = new Shipping();
//		shipping.handle(cmd);
//		repository.insert(shipping);
//		
//		EventStub.publisher(new ShippingCreated(shipping.getId(), shipping.getVehicle(), shipping.getStatus()));
//	}
//	
//	@CommandHandle
//	public void handle(RemoveShippingCommand cmd) {
//		Shipping shipping = new Shipping();
//		shipping.handle(cmd);
//		repository.delete(cmd.getIdShipping());
//		
//		//publicar evento
//	}
//	
//	@CommandHandle
//	public void handle(ShippingUpdateStatusCommand cmd) {
//		Shipping shipping = repository.findOne(cmd.getId());
//		shipping.handle(cmd);
//		repository.save(shipping);
//	}
//	
//
//	@EventHandle
//	public void teste(ShippingCreated evt) {
//		System.out.println("CRIADO ::: " + evt.getShipping());
//	}
//	
//	@EventHandle
//	public void teste(ShippingStatusUpdated evt) {
//		System.out.println("UPDATE ::: " + evt.getShipping());
//	}
//	
//	
//
//}
