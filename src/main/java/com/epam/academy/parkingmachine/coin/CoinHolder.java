package com.epam.academy.parkingmachine.coin;

import java.util.HashMap;
import java.util.Map;

import com.epam.academy.parkingmachine.Cassa;

public class CoinHolder {

	private Map<Integer, Integer> coins = new HashMap<>();
	private int sumValue = 0;

	public int getSumValue() {
		return sumValue;
	}

	public void addCoin(int coin) {
		this.addCoin(coin, 1);
	}

	public void addCoin(int coin, int amount) {
		Integer quantity = coins.get(coin);
		if (quantity == null) {
			quantity = amount;
		} else {
			quantity += amount;
		}

		coins.put(coin, quantity);
		sumValue += coin * amount;
	}

	public int getCoint(int coin) {
		return coins.get(coin);
	}

	public void removeCoin(int coin, int amount) throws Exception {
		if (amount == 0) {
			return;
		}

		Integer quantity = coins.get(coin);

		if (quantity == null || quantity < amount) {
			throw new Exception("Not enough coin to remove!");
		} else {
			quantity -= amount;
		}
		
		coins.put(coin, quantity);
		sumValue -= coin * amount;
	}

	public void printCoins() {
		for (Integer i : coins.keySet()) {
			System.out.println("Coin: " + i + ", quantity: " + coins.get(i));
		}
	}

	public boolean isEmpty() {
		return coins.isEmpty();
	}

	public void addCoinHolder(CoinHolder coinHolder) {
		for (Map.Entry<Integer, Integer> coinEntry : coinHolder.coins.entrySet()) {
			addCoin(coinEntry.getKey(), coinEntry.getValue());
		}
	}

	public void substractCoinHolder(CoinHolder coinHolder) throws Exception {
		for (Map.Entry<Integer, Integer> coinEntry : coinHolder.coins.entrySet()) {
			removeCoin(coinEntry.getKey(), coinEntry.getValue());
		}
	}
	
	public CoinHolder addWith(CoinHolder coinHolder) {
		CoinHolder myHolder = new CoinHolder();
		
		myHolder.addCoinHolder(coinHolder);
		myHolder.addCoinHolder(this);
		
		return myHolder;
	}
	
	public CoinHolder collectCoinsEqual(int amount) throws Exception {
		CoinHolder result = new CoinHolder();
		
		for(int i = Cassa.availableCoins.length; i > 0; i--) {
			int coinType = Cassa.availableCoins[i];
			int amountOfType = this.getCoint(coinType);
			
			while (coinType <= amount && amountOfType > 0) {
				removeCoin(coinType, 1);
				
				result.addCoin(coinType);
				
				amount -= coinType;
			}
		}
		
		if(amount > 0) {
			addCoinHolder(result);
		}
		
		return result;
	}
}
