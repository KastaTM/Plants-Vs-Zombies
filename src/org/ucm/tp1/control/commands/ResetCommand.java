package org.ucm.tp1.control.commands;

import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class ResetCommand extends Command{
	public ResetCommand() {
		super("reset","r","","[r]eset: reset game");
	}
	
	public boolean execute(Game g) {
		g.reset();
		return true;
	}
	
	public Command parse(String[] words) throws CommandParseException {
		  return this.parseNoParamsCommand(words);
	}
}
