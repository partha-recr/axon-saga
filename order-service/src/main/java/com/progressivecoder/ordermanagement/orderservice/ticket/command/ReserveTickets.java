package com.progressivecoder.ordermanagement.orderservice.ticket.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;


public class ReserveTickets {
	@TargetAggregateIdentifier
	public String showId;	

	public String reservationId;

	public int amount;
	
	public ReserveTickets(String showId, String reservationId, int amount) {
		super();
		this.showId = showId;
		this.reservationId = reservationId;
		this.amount = amount;
	}
}
