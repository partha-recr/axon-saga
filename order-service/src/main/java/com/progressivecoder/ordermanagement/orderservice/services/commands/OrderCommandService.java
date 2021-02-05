package com.progressivecoder.ordermanagement.orderservice.services.commands;

import com.progressivecoder.ordermanagement.orderservice.dto.commands.OrderCreateDTO;
import com.progressivecoder.ordermanagement.orderservice.ticket.command.ConfirmReservation;
import com.progressivecoder.ordermanagement.orderservice.ticket.command.PlanShow;
import com.progressivecoder.ordermanagement.orderservice.ticket.command.ReserveTickets;

import java.util.concurrent.CompletableFuture;

public interface OrderCommandService {

    public CompletableFuture<String> createOrder(OrderCreateDTO orderCreateDTO);

	public CompletableFuture<String> bookTicket(PlanShow planShow);	

	public CompletableFuture<String> reserveTicket(ReserveTickets reserveTickets);
	
	public CompletableFuture<String> confirmTicket(ConfirmReservation confirmReservation);

}
