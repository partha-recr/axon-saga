package com.progressivecoder.ordermanagement.orderservice.ticket.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.springframework.stereotype.Component;

public class PlanShow {
	@TargetAggregateIdentifier
	public String showId;

	public String name;
	
	public String time;

	public int availableTickets;
	
	public PlanShow(String showId, String name, String time, int availableTickets) {
		super();
		this.showId = showId;
		this.name = name;
		this.time = time;
		this.availableTickets = availableTickets;
	}
}
