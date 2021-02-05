package com.progressivecoder.ordermanagement.orderservice.ticket.event;

public class TicketsReserved {
	public String showId;

	public String reservationId;

	public int amount;

	public TicketsReserved(String showId, String reservationId, int amount) {
		super();
		this.showId = showId;
		this.reservationId = reservationId;
		this.amount = amount;
	}
}
