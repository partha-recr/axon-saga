package com.progressivecoder.ordermanagement.orderservice.controllers;

import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.progressivecoder.ordermanagement.orderservice.services.commands.OrderCommandService;
import com.progressivecoder.ordermanagement.orderservice.ticket.command.ConfirmReservation;
import com.progressivecoder.ordermanagement.orderservice.ticket.command.PlanShow;
import com.progressivecoder.ordermanagement.orderservice.ticket.command.ReserveTickets;

@RestController
@RequestMapping(value = "/api/ticket")
public class TicketBookingController {
	
	private OrderCommandService orderCommandService;

    public TicketBookingController(OrderCommandService orderCommandService) {
        this.orderCommandService = orderCommandService;
    }
    
    @PostMapping("/book")
    public CompletableFuture<String> bookTicket(@RequestBody PlanShow planShow){
        return orderCommandService.bookTicket(planShow);
    }
    
    @PostMapping("/reserve")
    public CompletableFuture<String> reserveTicket(@RequestBody ReserveTickets reserveTickets){
        return orderCommandService.reserveTicket(reserveTickets);
    }
    
    @PostMapping("/confirm")
    public CompletableFuture<String> confirmTicket(@RequestBody ConfirmReservation confirmReservation){
        return orderCommandService.confirmTicket(confirmReservation);
    }
    
    

}
