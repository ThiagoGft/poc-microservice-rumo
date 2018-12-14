package br.com.rumo.poc.delivery.domain.query;

import java.time.Month;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.rumo.poc.delivery.domain.command.ConfirmDeliveryCommand;
import br.com.rumo.poc.delivery.domain.event.DeliveryConfirmed;
import br.com.rumo.poc.delivery.domain.event.DeliveryCreated;
import br.com.rumo.poc.delivery.infraestruture.data.DeliveryQueryClientId;
import br.com.rumo.poc.delivery.infraestruture.data.DeliveryQueryClientIdRepository;
import br.com.rumo.poc.sharedkernel.command.CommandStub;
import br.com.rumo.poc.sharedkernel.eventsource.EventHandle;

@Component
public class SeviceEventHandler {
	
	@Autowired
	DeliveryQueryClientIdRepository queryRepository;
	
	@EventHandle
	public void handle(DeliveryConfirmed event) {
		
		final Month month = event.getConfirmationDate().getMonth();
		final UUID clientId = event.getClientId();
		
		DeliveryQueryClientId data = queryRepository.findOneByClientIdAndMonth(clientId, month);
		
		if(data == null) {
			data = new DeliveryQueryClientId();
			data.setClientId(event.getClientId());
			data.setMonth(event.getConfirmationDate().getMonth());
			data.setAmount(event.getPrice());
			
		}else {
			data.setAmount(data.getAmount().add(event.getPrice()));
		}
		
		queryRepository.save(data);
		
		System.out.println("QUERY :: " + queryRepository.findAll());
	}
	
	@EventHandle
	public void handle(DeliveryCreated event) {
		System.out.println("CRIADO !!!!");
		CommandStub.publisher(new ConfirmDeliveryCommand(event.getId()));
	}

}
