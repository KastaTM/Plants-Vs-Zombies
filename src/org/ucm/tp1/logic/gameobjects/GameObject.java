package org.ucm.tp1.logic.gameobjects;

import org.ucm.tp1.logic.Game;

public abstract class GameObject implements IAttack {
	protected int health;
	protected int damage;
	protected int x;
	protected int y;	
	protected Game g;
	
	public GameObject(int y, int x, Game g) {																		//Constructor GameObject
		this.y = y;
		this.x = x;
		this.g = g;
	}
	
	public abstract void attack(); 																					//Objetos atacan
	
	public abstract void update(); 																					//vampire se mueve slayer no hace nada
	
	public abstract String toString(); 																				//cada uno se printea de manera distinta
	
	public abstract String toSerialize();
	
	protected void receiveDamage(int damage) {
		this.health -= damage;
	}
	
	public boolean isAlive() {
		return this.health > 0;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public int getHealth() {
		return health;
	}
	
}