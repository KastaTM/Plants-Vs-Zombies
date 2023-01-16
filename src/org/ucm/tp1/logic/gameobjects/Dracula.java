package org.ucm.tp1.logic.gameobjects;

import org.ucm.tp1.logic.Game;

public class Dracula extends Vampire{
	
	private static boolean DraculaAlive;
	
	public Dracula(int y, int x, Game game) { 										//Constructor del Dracula
		super(y, x, game);
		this.health = 5;
		this.cycle = 0;
	}
	
	public String toString() {
		return "D [" + this.health + "]";
	}
	
	public String toSerialize() {
		return "D;" + this.x + ";" + this.y + ";" + this.health + ";" + this.cycle%2; 
	}
	
	public void attack() {															//Ataque de Dracula que mata instantaneo
		if(DraculaAlive) {
			IAttack ia;
			ia = g.attackableInPosition(this.y, this.x-1);
			if(ia != null) {
				ia.receiveDraculaAttack();
			}
		}
	}
	
	public boolean isDraculaDead() {
		return this.health <= 0;
	}
	
	public boolean receiveSlayerAttack(int damage) {								//Recibe el ataque de un slayer
		super.receiveSlayerAttack(damage);
		if(isDraculaDead()) {
			DraculaAlive=false;
		}
		return true;
	}
	
	public boolean receiveGarlicPush() {											//Recibe el push de un garlic
		super.receiveGarlicPush();
		if(isDraculaDead()) {
			DraculaAlive=false;
		}
		return true;
	}
	
	public boolean receiveLightFlash() {
		return false;
	}

	public static boolean isDraculaAlive() {
		return DraculaAlive;
	}
	

	public static void setDraculaAlive(boolean draculaAlive) {
		DraculaAlive = draculaAlive;
	}

	
	
	
}