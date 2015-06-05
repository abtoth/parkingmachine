package com.epam.academy.parkingmachine;

import java.util.Arrays;

import com.epam.academy.parkingmachine.coin.CoinHolder;

public class Cassa {

	public static final Integer[] availableCoins = { 5, 10, 20, 50, 100, 200, 500, 1000,
			2000, 5000, 10000, 20000 };

	private CoinHolder cassa = new CoinHolder();

	{
		cassa.addCoin(5, 100);
		cassa.addCoin(10, 50);
		cassa.addCoin(20, 0);
		cassa.addCoin(50, 40);
		cassa.addCoin(100, 30);
		cassa.addCoin(200, 30);
		cassa.addCoin(500, 25);
		cassa.addCoin(1000, 25);
		cassa.addCoin(2000, 20);
		cassa.addCoin(5000, 20);
		cassa.addCoin(10000, 15);
		cassa.addCoin(20000, 10);
	}

	public CoinHolder getCassa() {
		return cassa;
	}

	public boolean isCoin(int number) {
		return Arrays.asList(availableCoins).contains(number);
	}

	public int getCoinQuantity(int coin) {
		if (!isCoin(coin)) {
			return 0;
		}

		return cassa.getCoint(coin);
	}

	public void putCoins(int coin, int quantity) {
		if (!isCoin(coin)) {
			return;
		}

		cassa.addCoin(coin, quantity);
	}

	public void removeCoins(CoinHolder coins) throws Exception {
		cassa.substractCoinHolder(coins);
	}

}
