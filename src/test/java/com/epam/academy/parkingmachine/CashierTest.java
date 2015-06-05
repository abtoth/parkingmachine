package com.epam.academy.parkingmachine;

import static org.testng.Assert.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.academy.parkingmachine.coin.Coin;
import com.epam.academy.parkingmachine.coin.CoinHolder;

public class CashierTest {
	
	private Cashier cashier;

	@BeforeMethod
	public void CreateCashier(){
		cashier = new Cashier();
	} 
	
	@Test( expectedExceptions = {CannotRefundException.class})
	public void testCalculateRefundCanNotRefund() throws CannotRefundException, Exception {

		CoinHolder coinHolder = new CoinHolder();

		cashier.calculateRefund(coinHolder, 10000000);	
	}
	
	@Test
	public void testCalculateRefundReturnsCorrectRefund() throws CannotRefundException, Exception {
		
		CoinHolder coinHolder = new CoinHolder();

		CoinHolder refund = cashier.calculateRefund(coinHolder, 1520);	
		
		assertEquals(refund.getSumValue(), 1520, refund.getSumValue() + "The value of refund is different");
	}
}
