package br.com.rumo.poc.delivery.infraestruture.data;

import java.math.BigDecimal;
import java.time.Month;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class DeliveryQueryClientId {
	
	@Id
	private UUID clientId;
	private Month month;
	private BigDecimal amount;

}
