package com.progressivecoder.ordermanagement.orderservice.ticket.event;

public class ReservationExpired {
	public String reservationId;

	public ReservationExpired(String reservationId) {
		super();
		this.reservationId = reservationId;
	}
}
