package org.ucm.tp1.control.commands;

import org.ucm.tp1.exceptions.CommandExecuteException;
import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class AddCommand extends Command{
	private int x;
	private int y;
	
	public AddCommand() {
		super("add","a","","[a]dd <x> <y>: add a slayer in position x, y");
		this.x = -1;
		this.y = -1;
	}
	
	public boolean execute(Game g) throws CommandExecuteException{
		boolean ret = false;
		try {
		if(g.addSlayer(y, x)) {
			g.updateGame();
			ret=true;
			}
		}
		catch(CommandExecuteException ex) {
			System.out.println(ex.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: Failed to add slayer"));
			}
		return ret;
	}
	
	public Command parse(String[] words) throws CommandParseException{
		if (matchCommandName(words[0])) {
			if (words.length != 3) {
				//System.err.println("[ERROR]: Incorrect number of arguments for add command: [a]dd <x> <y>" + "\n");
				throw new CommandParseException ("[ERROR]: Incorrect number of arguments for add command: [a]dd <x> <y>" + "\n");
				//return null;
			}
			else {
				try {
					x = Integer.parseInt(words[1]);
					y = Integer.parseInt(words[2]);
				}
				catch(NumberFormatException ex) {
					//System.out.println(ex.getMessage());
					//throw new CommandParseException ("[ERROR]: " + incorrectArgsMsg + " %n %n");
					throw new CommandParseException ("[ERROR]: Unvalid argument for add slayer command, number expected: [a]dd <x> <y>");
				}
			}
			return this;
		}
		
		return null;
	  }
}
