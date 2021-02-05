package com.progressivecoder.ordermanagement.orderservice.services.commands;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import com.progressivecoder.ecommerce.commands.CreateOrderCommand;
import com.progressivecoder.ordermanagement.orderservice.aggregates.OrderStatus;
import com.progressivecoder.ordermanagement.orderservice.dto.commands.OrderCreateDTO;
import com.progressivecoder.ordermanagement.orderservice.ticket.command.ConfirmReservation;
import com.progressivecoder.ordermanagement.orderservice.ticket.command.PlanShow;
import com.progressivecoder.ordermanagement.orderservice.ticket.command.ReserveTickets;

@Service
public class OrderCommandServiceImpl implements OrderCommandService {

    private final CommandGateway commandGateway;

    public OrderCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<String> createOrder(OrderCreateDTO orderCreateDTO) {
        return commandGateway.send(new CreateOrderCommand(UUID.randomUUID().toString(), orderCreateDTO.getItemType(),
                orderCreateDTO.getPrice(), orderCreateDTO.getCurrency(), String.valueOf(OrderStatus.CREATED)));
    }
    
    @Override
    public CompletableFuture<String> bookTicket(PlanShow planShow) {
        return commandGateway.send(planShow);
    }

	@Override
	public CompletableFuture<String> reserveTicket(ReserveTickets reserveTickets) {
		return commandGateway.send(reserveTickets);
	}

	@Override
	public CompletableFuture<String> confirmTicket(ConfirmReservation confirmReservation) {
		return commandGateway.send(confirmReservation);
	}
}
