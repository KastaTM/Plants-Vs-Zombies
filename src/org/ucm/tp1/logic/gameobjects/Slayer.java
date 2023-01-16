package org.ucm.tp1.logic.gameobjects;

import org.ucm.tp1.logic.Game;

public class Slayer extends GameObject{
	public static int money = 50;  
	
	public Slayer(int y, int x, Game game) {											//Constructor del Slayer
		super(y,x,game);
		this.health = 3;
		this.damage = 1; 
	}
		
	public String toString() {
		return "S ["+this.health+"]";
	}
	
	public void update() {}
	
	public void attack() {																//Ataque del slayer a un unico objetivo
		if(health > 0) {
		IAttack ia;
		int pos = this.x + 1;
		boolean enc = false;
		while(pos < this.g.getCols() && !enc) {
			ia = g.attackableInPosition(this.y, pos);
			if(ia != null) {
				if(ia.receiveSlayerAttack(damage)) {
					enc = true;
				}
			}
			pos++;
			}
		}
	}
	
	public boolean receiveVampireAttack(int damage) {									//Recibe ataque de un vampiro
		receiveDamage(damage);
		return true;
	}
	
	public boolean receiveDraculaAttack() {												//Recibe ataque de Dracula
		receiveDamage(this.health);
		return true;
	}
	
	public String toSerialize() {
		return "S;"+this.x+";"+this.y+";"+this.health;
	}
	
}