package com.progressivecoder.ordermanagement.orderservice.ticket.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;


public class ConfirmReservation {
	@TargetAggregateIdentifier
	public String showId;

	public String reservationId;

	public ConfirmReservation(String showId, String reservationId) {
		super();
		this.showId = showId;
		this.reservationId = reservationId;
	}
}
