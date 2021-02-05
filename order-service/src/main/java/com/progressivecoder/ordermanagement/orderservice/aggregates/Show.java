package com.progressivecoder.ordermanagement.orderservice.aggregates;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.deadline.DeadlineManager;
import org.axonframework.deadline.annotation.DeadlineHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.MetaData;
import org.axonframework.messaging.annotation.MetaDataValue;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import com.progressivecoder.ordermanagement.orderservice.ticket.command.ConfirmReservation;
import com.progressivecoder.ordermanagement.orderservice.ticket.command.PlanShow;
import com.progressivecoder.ordermanagement.orderservice.ticket.command.ReserveTickets;
import com.progressivecoder.ordermanagement.orderservice.ticket.event.Reservation;
import com.progressivecoder.ordermanagement.orderservice.ticket.event.ReservationConfirmed;
import com.progressivecoder.ordermanagement.orderservice.ticket.event.ReservationExpired;
import com.progressivecoder.ordermanagement.orderservice.ticket.event.ShowPlanned;
import com.progressivecoder.ordermanagement.orderservice.ticket.event.TicketsReserved;

@Aggregate
public class Show {

	@AggregateIdentifier
	public String showId;

	public String name;
	public String time;

	public int availableTickets;

	public Map<String, Reservation> reservations;

	public Show() {
	}

	@CommandHandler
	public Show(PlanShow command) {
		apply(new ShowPlanned(command.showId, command.name, command.time, command.availableTickets));
	}

	@EventHandler
	public void onPlanned(ShowPlanned event) {
		showId = event.showId;
		name = event.name;
		time = event.time;
		availableTickets = event.availableTickets;
		reservations = new HashMap<>();
	}

	@CommandHandler
	public void reserveTickets(ReserveTickets command, DeadlineManager deadlineManager) {
		if (!(reservations.containsKey(command.reservationId) || (availableTickets < command.amount))) {
			String reservationDeadlineId = deadlineManager.schedule(Duration.ofMinutes(3), "reservation",
					command.reservationId);

			apply(new TicketsReserved(command.showId, command.reservationId, command.amount),
					MetaData.with("reservationDeadlineId", reservationDeadlineId));
		}
	}

	@EventHandler
	public void onTicketsReserved(TicketsReserved event,
			@MetaDataValue("reservationDeadlineId") String reservationDeadlineId) {
		availableTickets -= event.amount;

		reservations.put(event.reservationId,
				new Reservation(event.reservationId, event.amount, reservationDeadlineId));
	}

	@CommandHandler
	public void confirmReservation(ConfirmReservation command, DeadlineManager deadlineManager) {
		deadlineManager.cancelSchedule("reservation", reservations.get(command.reservationId).deadlineId);

		apply(new ReservationConfirmed(command.reservationId));
	}

	@EventHandler
	public void onReservationConfirmed(ReservationConfirmed event) {
		System.out.println("=======Reservation confirmed! Email the tickets." + event.reservationId);
		reservations.remove(event.reservationId);
	}

	@DeadlineHandler
	public void handleReservationExpired(String reservationId) {
		System.out.println("=====DEADLINE=====" + reservationId);
		apply(new ReservationExpired(reservationId));
	}

	@EventHandler
	public void onReservationExpired(ReservationExpired event) {
		System.out.println("====Reservation expired! Reserved tickets are for sale again." + event.reservationId);
		Reservation reservation = reservations.remove(event.reservationId);

		availableTickets += reservation.amountOfTickets;
	}

}
