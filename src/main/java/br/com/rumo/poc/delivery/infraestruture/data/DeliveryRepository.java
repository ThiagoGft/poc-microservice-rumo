package br.com.rumo.poc.delivery.infraestruture.data;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.rumo.poc.delivery.domain.DeliveryAggregator;

public interface DeliveryRepository extends MongoRepository<DeliveryAggregator, UUID>{

}
