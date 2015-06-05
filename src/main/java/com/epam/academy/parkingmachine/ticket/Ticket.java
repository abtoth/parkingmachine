package com.epam.academy.parkingmachine.ticket;

public class Ticket {

	public enum ticketStatus {
		GENERATED,
		UNDER_PAYMENT,
		PAID
	}
	
	private static int idCounter = 0;

	private int id = ++idCounter;
	private ticketStatus status;
	private int fee;
	
	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ticketStatus getStatus() {
		return status;
	}

	public void setStatus(ticketStatus status) {
		this.status = status;
	}

	public Ticket() {
		status = ticketStatus.GENERATED;
	}
}
