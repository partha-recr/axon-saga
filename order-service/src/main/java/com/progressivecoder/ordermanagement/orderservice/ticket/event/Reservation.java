package com.progressivecoder.ordermanagement.orderservice.ticket.event;

public class Reservation {
	public String reservationId;

	public int amountOfTickets;	

	public String deadlineId;
	
	public Reservation(String reservationId, int amountOfTickets, String deadlineId) {
		super();
		this.reservationId = reservationId;
		this.amountOfTickets = amountOfTickets;
		this.deadlineId = deadlineId;
	}
}
