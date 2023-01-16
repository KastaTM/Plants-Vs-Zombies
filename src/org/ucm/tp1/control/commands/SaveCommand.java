package org.ucm.tp1.control.commands;

import java.io.FileWriter;
import java.io.IOException;
import org.ucm.tp1.exceptions.CommandExecuteException;
import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class SaveCommand extends Command{
	private String filename = "";
	
	public SaveCommand() {
		super("save","s","","[S]ave <filename>: Save the state of the game to a file.");
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			FileWriter file = new FileWriter(filename + ".dat");
			file.write("Buffy the Vampire Slayer v3.0");
			file.write(game.toSerialize());
			file.close();
			System.out.println("Game successfully saved to file " + filename + ".dat.");
			return false;
		}
		catch(IOException io) {
			System.out.println("[ERROR]: Problem to save game");
			throw new CommandExecuteException("[ERROR]: Failed to save game");
			
		}
	}
	
	public Command parse (String[] words) throws CommandParseException {
		if (matchCommandName(words[0])) {
			if (words.length != 2) {
				//System.err.println(incorrectArgsMsg);
				throw new CommandParseException("[ERROR]: Command " + name+" :"+incorrectNumberOfArgsMsg);
				//return null;
			}
			else {
				filename = words[1];
			}
			return this;
		}
		
		return null;
	  }

}

