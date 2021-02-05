package com.progressivecoder.ordermanagement.orderservice.ticket.event;

public class ReservationConfirmed {
	public String reservationId;

	public ReservationConfirmed(String reservationId) {
		super();
		this.reservationId = reservationId;
	}
}
