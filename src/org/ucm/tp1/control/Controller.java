package org.ucm.tp1.control;

import java.util.Scanner;
import org.ucm.tp1.control.commands.Command;
import org.ucm.tp1.control.commands.CommandGenerator;
import org.ucm.tp1.exceptions.GameException;
import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.gameobjects.Vampire;

public class Controller {

	
	public final String prompt = "Command > "; 
	public static final String helpMsg = String.format(
			"Available commands:%n" +
			"[a]dd <x> <y>: add a slayer in position x, y%n" +
			"[h]elp: show this help%n" + 
			"[r]eset: reset game%n" + 
			"[e]xit: exit game%n"+ 
			"[n]one | []: update%n");
	
	public static final String unknownCommandMsg = String.format("Unknown command");
	public static final String invalidCommandMsg = String.format("Invalid command"); 
	public static final String invalidPositionMsg = String.format("Invalid position");

    private Game game;
    private Scanner scanner;
    
    public Controller(Game game, Scanner scanner) {
	    this.game = game; 
	    this.scanner = scanner; 
    }
    
    public void  printGame() { 
   	 System.out.println(game);
   }
    
    public void run() {
    	game.inicializeList();
    	boolean refreshDisplay=true;
    	Command command = null;
    while (!game.isFinished() && !game.isEnd()){
    	if (refreshDisplay ) printGame();
    	refreshDisplay = false;
    	System.out.println(prompt);
    	String s = scanner.nextLine();
    	String[] parameters = s.toLowerCase().trim().split(" ");
    	System.out.println("[DEBUG] Executing: " + s);
    	try {
	    	command = CommandGenerator.parseCommand(parameters);
	    	if (command != null) {
	    		refreshDisplay = command.execute(game);
	    	}
	    	else {
	    		System.out.println("[ERROR]: "+ unknownCommandMsg);
	    	}
	    	if(game.isFinished()) {
		    	printGame();
		    	if(Vampire.isVampireEnd()) {
					System.out.println("[GAME OVER] Vampires win!");
				}
				else if(game.playerWins()){
					System.out.println("[GAME OVER] Player wins");
				}
	    	}
    	}
    	catch(GameException ex) {
    		System.out.println(ex.getMessage());
    	}
    	}
    }
}	