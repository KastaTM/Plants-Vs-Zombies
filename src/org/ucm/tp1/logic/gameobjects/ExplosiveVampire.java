package org.ucm.tp1.logic.gameobjects;

import org.ucm.tp1.logic.Game;

public class ExplosiveVampire extends Vampire{

	public ExplosiveVampire(int y, int x, Game game){								//Constructor explosive vampire
		super(y, x, game);
	}
	
	public String toString() {														//devuelve el formato string para el tablero con la vida del vampire
		return "EV [" + this.health + "]";
	}
	
	public boolean receiveLightFlash() {
		super.receiveLightFlash();
		return true;
	}
	
	public void explosion() {														//Explosión del vampiro recorriendo las columnas adaycentes
		IAttack ia;
		for(int j = this.y - 1; j < this.y + 2; ++j) {
			for(int i = this.x - 1; i < this.x + 2; ++i) {
				if(i != super.getX() && j != super.getY()) {
					ia = g.attackableInPosition(i, j);
					if(ia != null) {
						ia.receiveSlayerAttack(1);					}
				}
			}
		}
	}
	
	public String toSerialize() {
		return "EV;"+this.x+";"+this.y+";"+this.health+";"+this.cycle%2;
	}
	
}


