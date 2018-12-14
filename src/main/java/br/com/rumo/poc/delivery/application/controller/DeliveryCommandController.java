package br.com.rumo.poc.delivery.application.controller;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.rumo.poc.delivery.domain.command.AddShippingCommand;
import br.com.rumo.poc.delivery.domain.command.ConfirmDeliveryCommand;
import br.com.rumo.poc.delivery.domain.command.CreateDeliveryCommand;
import br.com.rumo.poc.sharedkernel.command.CommandStub;

@RestController
@RequestMapping("delivery/command")
public class DeliveryCommandController {
	
	@PostMapping("create")
	public void createDelivery(@RequestBody CreateDeliveryCommand cmd) {
		CommandStub.publisher(cmd);
	}
	
	@DeleteMapping("confirm")
	public void cancelDelivery(@RequestParam String id) {
		CommandStub.publisher(new ConfirmDeliveryCommand(UUID.fromString(id)));
	}
	
	@PutMapping("add-shipping")
	public void addShipping(@RequestBody AddShippingCommand cmd) {
		CommandStub.publisher(cmd);
	}
	
	@GetMapping("uuid")
	public String getUUID() {
		return UUID.randomUUID().toString();
	}
	

}
