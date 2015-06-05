package com.epam.academy.parkingmachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import com.epam.academy.parkingmachine.coin.CoinHolder;
import com.epam.academy.parkingmachine.ticket.Ticket;
import com.epam.academy.parkingmachine.ticket.TicketService;
import com.epam.academy.parkingmachine.ticket.Ticket.ticketStatus;

public class Cashier implements Runnable {

	private enum CashierProcessStatus {
		NOT_ENOUGH_COINS,
		ENOUGH_COINS,
		CAN_NOT_REFUND		
	}
	
	private Cassa cassa = new Cassa();
	private TicketService ticketService = new TicketService();

	@Override
	public void run() {

		Ticket ticket;

		for (int i = 1; i < 10; i++) {
			ticketService.generateTicket();
		}

		while (true) {

			if ((ticket = readInTicketId()) == null) {
				System.out.println("Not a valid ticket id!");
			} else {
				ticketService.calculateParkingFee(ticket);
				
				try {
					readInCoins(ticket);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Ticket readInTicketId() {

		System.out.println("Please input your ticket id:");
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		try {
			line = inputReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ticketService.getTicketById(Integer.parseInt(line));

	}

	public void readInCoins(Ticket ticket) throws Exception {

		System.out.println("Please pay " + ticket.getFee());
		ticket.setStatus(Ticket.ticketStatus.UNDER_PAYMENT);

		CoinHolder insertedCoins = new CoinHolder();
		
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		String line;
		try {
			line = inputReader.readLine();
			while (!("".equals(line))) {
				int insertedCoinValue = Integer.parseInt(line);
				if (!cassa.isCoin(insertedCoinValue)) {
					System.out.println("Not a valid coin!");
				} else {
					CashierProcessStatus returnStatus = handleInsertedCoin(ticket, insertedCoins, insertedCoinValue);

					if (returnStatus == CashierProcessStatus.ENOUGH_COINS) {
						break;
					} else if (returnStatus == CashierProcessStatus.CAN_NOT_REFUND) {
						break;
					} else {
						System.out.println("need more money");
					}

				}
				line = inputReader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public CashierProcessStatus handleInsertedCoin(Ticket ticket, CoinHolder insertedCoins, int insertedCoinValue) throws Exception {

		insertedCoins.addCoin(insertedCoinValue);
		if (insertedCoins.getSumValue() >= ticket.getFee()) {
			int refundAmount = ticket.getFee() - insertedCoinValue;
			
			CoinHolder refund = calculateRefund(insertedCoins, refundAmount);

			if (refund != null) {

				if (!refund.isEmpty()) {
					cassa.removeCoins(refund);
					System.out.println("Refund: ");
					refund.printCoins();
				} else {
					System.out.println("No refund...");
				}
				System.out.println("Thank you! Bye!");
				ticket.setStatus(ticketStatus.PAID);
				return CashierProcessStatus.ENOUGH_COINS;
			} else {
				System.out.println("I don't have enough coins to refund the change. ");
				return CashierProcessStatus.CAN_NOT_REFUND;
			}
		}
		
		return CashierProcessStatus.NOT_ENOUGH_COINS;
	}

	public CoinHolder calculateRefund(CoinHolder insertedCoins, int refundAmount) {
		CoinHolder totalCoins = insertedCoins.addWith(cassa.getCassa());
		
		
		/*
		 * Map<Integer, Integer> returnCoins = new HashMap<>();
		 * 
		 * int insertedSum = getCoinsValue(insertedCoins); int returnValue =
		 * insertedSum - fee;
		 * 
		 * for(Integer i:cassa.getCassa().keySet()) {
		 * 
		 * 
		 * 
		 * 
		 * 
		 * }
		 */

		return null;
	}

	private int getCoinsValue(Map<Integer, Integer> coins) {
		int sum = 0;
		for (Integer i : coins.keySet()) {
			sum += coins.get(i) * i;
		}
		return sum;
	}

}
