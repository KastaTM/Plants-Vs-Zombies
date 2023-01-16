package org.ucm.tp1.control.commands;

import org.ucm.tp1.exceptions.CommandExecuteException;
import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class AddBloodBankCommand extends Command{
	private int x;
	private int y;
	private int z;
	
	public AddBloodBankCommand() {
		super("bank","b","","[b]ank <x> <y> <z>: add a blood bank with cost z in position x, y");
		this.x = -1;
		this.y = -1;
	}
	
	public boolean execute(Game g) throws CommandExecuteException {
		boolean ret = false;
		try {
			if(g.addBloodBank(y, x, z)) {
				g.updateGame();
				ret=true;
			}
		}
		catch(CommandExecuteException ex) {
			System.out.println(ex.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: Failed to add bank"));
		}
		return ret;

	}
	
	public Command parse(String[] words) throws CommandParseException {
		if (matchCommandName(words[0])) {
			if (words.length != 4) {
				System.err.println(incorrectArgsMsg);
				return null;
			}
			else {
				try {
					x = Integer.parseInt(words[1]);
					y = Integer.parseInt(words[2]);
					z = Integer.parseInt(words[3]);
				}
				catch(NumberFormatException ex) {
					System.out.println(ex.getMessage());
					throw new CommandParseException ("[ERROR]: " + incorrectArgsMsg + " %n %n");
				}
			}
			return this; 
		}
		return null;
	  }
}
