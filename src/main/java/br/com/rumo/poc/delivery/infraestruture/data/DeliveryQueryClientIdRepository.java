package br.com.rumo.poc.delivery.infraestruture.data;

import java.time.Month;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeliveryQueryClientIdRepository extends MongoRepository<DeliveryQueryClientId, UUID>{
	
	public DeliveryQueryClientId findOneByClientIdAndMonth(UUID clientId, Month month);

}
