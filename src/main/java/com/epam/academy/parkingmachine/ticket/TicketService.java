package com.epam.academy.parkingmachine.ticket;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.epam.academy.parkingmachine.ticket.Ticket.ticketStatus;

public class TicketService {

	private Set<Ticket> tickets = new HashSet<Ticket>();
	private Random random = new Random();

	public Ticket generateTicket() {
		Ticket ticket = new Ticket();
		tickets.add(ticket);
		return ticket;
	}

	public Ticket getTicketById(int id) {
		for (Ticket ticket : tickets) {
			if (id == ticket.getId()) {
				return ticket;
			}
		}
		return null;
	}

	public boolean isTicketIdExists(int id) {
		for (Ticket ticket : tickets) {
			if (id == ticket.getId()) {
				return true;
			}
		}
		return false;
	}

	public void payTicket(int id) {
		Ticket ticket = getTicketById(id);
		if (ticket != null) {
			ticket.setStatus(ticketStatus.PAID);
		}
	}

	public void calculateParkingFee(Ticket ticket) {
		if (ticket != null) {
			ticket.setFee(random.nextInt(1000) * 5 + 5);
		}
	}

}
