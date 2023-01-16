package org.ucm.tp1.logic.gameobjects;

import org.ucm.tp1.logic.Game;

public class Vampire extends GameObject{
	protected int cycle;
	private static int VampiresBoard = 0; 
	protected static int remainingVampires;
	private static boolean vampireEnd=false;
	
	public Vampire(int y, int x, Game game) {											//Constructor del Vampire
		super(y,x,game);
		this.health = 5; 
		this.damage = 1; 
		this.cycle = 0;
		remainingVampires--;
		VampiresBoard++;    
	}
	
	public String toString() {															//Devuelve el formato string para el tablero con la vida del vampire
		return "V [" + this.health + "]";
	}
	
	public String toSerialize() {
		return "V;" + this.x + ";" + this.y + ";" + this.health + ";" + this.cycle%2; 
	}
	
	public void update() {
		if(!g.isCellOccupiedGame(this.y, x-1)) {
		cycle++; 
			if(cycle%2==0) {															//Si los ciclos son pares se moverá
				x--;																	//Se mueve una columna a la izquierda
				if(x<0)
					vampireEnd=true;
				}
			}
	}
	
	public void attack() {																//Ataque del vampiro
		if(health > 0) {
		IAttack ia;
			ia = g.attackableInPosition(this.y, this.x-1); 								//buscar slayer en la fila
			if(ia != null) {
				ia.receiveVampireAttack(damage);
			}
		}
	}
	
	public boolean receiveSlayerAttack(int damage) {									//Recibe el ataque de un slayer
		receiveDamage(damage);
		if(this.health <= 0) {
			VampiresBoard--;
		}
		return true;
	}
	
	public boolean receiveLightFlash() {										//Recibe el ataque de un light flash
		receiveDamage(this.health);
		if(this.health <= 0) {
			VampiresBoard--;
		}
		return true;
	}
	
	public boolean receiveGarlicPush() { 												//Recibe el push de un garlic
		if(x < g.getCols() - 1) {
			if(!g.isCellOccupiedGame(y, x + 1)) {										//si la celda no esta ocupada puede moverse
				x++;
				cycle = 0;
			}
		}
		else {
			this.health = 0;
			VampiresBoard--;
		}
		return true;
	}
	
	public static void init(int num){ 													//Método para inicializar vampiros en tablero y restantes
		 remainingVampires = num; 														//Vampiros restantes son los que les llega de level
		 VampiresBoard = 0; 
	} 
	
	public static void decreaseVampiresBoard() {
		VampiresBoard--;
	}
	
	public static void increaseVampiresBoard() {
		VampiresBoard++;
	}
	
	public static void decreaseRemainingVampires() {
		remainingVampires--;
	}
	
	public static int getVampiresBoard() {
		return VampiresBoard;
	}

	public static int getRemainingVampires() {
		return remainingVampires;
	}

	public static boolean isVampireEnd() {
		return vampireEnd;
	}
	

}