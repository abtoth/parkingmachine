package com.epam.academy.parkingmachine;

public class CannotRefundException extends Exception {

	public CannotRefundException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;

}
