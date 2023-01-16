package org.ucm.tp1.logic.gameobjects;

import org.ucm.tp1.logic.Game;

public class BloodBank extends GameObject{
	protected int money;
	
	public BloodBank(int y, int x, Game game, int z) {
			super(y,x,game);
			this.money = z;
			this.health = 1; 
			this.damage = 0; 
	}
	
	public String toString() { 
		return "B ["+this.money+"]";
	}
	
	public void update() {
		double coins=0.1*money;
		g.addCoins(coins);
	}
	
	public boolean receiveVampireAttack(int damage) {
		receiveDamage(damage);
		return true;
	}
	
	public boolean receiveDraculaAttack() {
		receiveDamage(this.health);
		return true;
	}

	public void attack() {}			//blood bank no ataca
	
	public String toSerialize() {
		return "B;"+this.x+";"+this.y+";"+this.health+";"+this.money;
	}
}
