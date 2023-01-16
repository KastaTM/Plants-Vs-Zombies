package org.ucm.tp1.logic;

public class Player {
	
	private int coins;
	
	public Player() {
		this.coins = 50; 
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins() {
		this.coins = 50;
	}
	
	public void addCoins(double coins) {
		this.coins+=coins;
	}
	
	public void decreaseCoins(int coins) {
		this.coins-=coins;
	}
}