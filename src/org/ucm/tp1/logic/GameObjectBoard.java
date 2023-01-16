package org.ucm.tp1.logic;

import org.ucm.tp1.logic.gameobjects.BloodBank;
import org.ucm.tp1.logic.gameobjects.IAttack;
import org.ucm.tp1.logic.gameobjects.Slayer;
import org.ucm.tp1.logic.gameobjects.Vampire;

public class GameObjectBoard {
	
	private GameObjectList listObjects;
	
	public GameObjectBoard(int num) {
	}
	
	//Funciones de transicción para el game object list
	
	public void inicializeListBoard() {
		listObjects = new GameObjectList();
	}
	
	public void updateBoard() {
			listObjects.update();
			listObjects.attack();
	}
	
	public IAttack attackableInPositionBoard(int y, int x) {
		return listObjects.find(y,x);
	}
		
	public boolean isCellOccupied(int y, int x) {
		return listObjects.isObjectHere(y, x);
	}
	
	public void addVampireBoard(Vampire v) {
		listObjects.add(v);
	}
	
	public void addSlayerBoard(Slayer s) {
		listObjects.add(s);
	}
	
	public void addBloodBankBoard(BloodBank b) {
		listObjects.add(b);
	}
	
	public void removeDead() {
		listObjects.removeDeads(); 
	}
	
	public void lightFlashBoard() {
		listObjects.LightFlashList();
	}
	
	public void garlicPushBoard(){
		listObjects.garlicPushList();
	}
	
	public String findObjectString(int y, int x) {
		String s = " ";
		if(listObjects.isObjectHere(y, x)) {
			s = listObjects.ObjectToString(y,x);
		}
		return s;
	}
	
	public String toSerialize() {
		return listObjects.listSerialize();
	}
}