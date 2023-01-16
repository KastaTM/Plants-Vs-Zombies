package org.ucm.tp1.logic;

import java.util.ArrayList;
import org.ucm.tp1.logic.gameobjects.GameObject;
import org.ucm.tp1.logic.gameobjects.IAttack;

public class GameObjectList {
		private ArrayList <GameObject> objects;
		
		public GameObjectList() {
			this.objects = new ArrayList <GameObject>();
		}
		
		public IAttack find(int y, int x) {												//Devuelve un objeto en formato interfaz
			IAttack o = search(y,x);
			if(o != null) {
				return o;
			}
			return null;
		}
		
		public boolean isObjectHere(int y, int x) {										//Función que devuelve si existe un objeto en la posición
			if(search(y,x) != null) {
				return true;
			}
			return false;
			
		}
		
		private IAttack search(int y, int x) {
			for(GameObject o: objects) {
				if(o.getX() == x && o.getY() == y) {
					return o;
				}
			}
			return null;
		}
		
		public String ObjectToString(int y, int x) {									//Devuelve el formato string del objeto en posición
			String s = " ";
			for(GameObject o: objects) {
				if(o.getX() == x && o.getY() == y) {
					s=o.toString();
				}
			}
			return s;
		}
		
		public void add(GameObject o) {													//Añade objeto a la lista
			this.objects.add(o);
		}
		
		public void update() {															//Realiza update en la lista
			for(GameObject o: objects) {
				o.update();
			}
		}
		
		public void attack() {															//Realiza los ataques en la lista
			for(GameObject o: objects) {
				o.attack();
			}
		}
		
		public void removeDeads() {														//Limpia los muertos del tablero
			int i = 0;
			while(i < objects.size()) {
				this.objects.get(i);
				if(!this.objects.get(i).isAlive()) {
					this.objects.remove(i);
				}
				else {
					i++;
				}
			}
		}
		
		public void LightFlashList() {													//Utilización de la light flash en la lista de objetos
			for(GameObject o: objects) {
				o.receiveLightFlash();
			}
		}
		
		public void garlicPushList() {													//Utilización del garlic push en la lista de objetos
			for(GameObject o: objects) {
				o.receiveGarlicPush();
			}
		}
		
		public String listSerialize() {
			String s = "";
			for(GameObject o: objects) {
				s += o.toSerialize() + "\n";
			}
			return s;
		}
}
	