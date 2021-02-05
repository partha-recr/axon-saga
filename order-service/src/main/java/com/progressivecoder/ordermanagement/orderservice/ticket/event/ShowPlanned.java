package com.progressivecoder.ordermanagement.orderservice.ticket.event;

import java.time.LocalDateTime;


public class ShowPlanned {
	public String showId;

	public String name;
	public String time;

	public int availableTickets;

	public ShowPlanned(String showId, String name, String time, int availableTickets) {
		super();
		this.showId = showId;
		this.name = name;
		this.time = time;
		this.availableTickets = availableTickets;
	}
}
