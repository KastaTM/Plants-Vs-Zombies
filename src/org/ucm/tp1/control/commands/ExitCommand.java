package org.ucm.tp1.control.commands;

import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class ExitCommand extends Command{
	public ExitCommand() {
		super("exit","e","","[e]xit: exit game");
	}
	
	public boolean execute(Game g) {
		g.setEnd(true);
		System.out.println("[Game over] Nobody wins...");
		return false;
	}
	
	public Command parse(String[] words) throws CommandParseException {
		  return this.parseNoParamsCommand(words);
	  }
}
