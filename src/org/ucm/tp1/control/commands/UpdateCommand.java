package org.ucm.tp1.control.commands;

import org.ucm.tp1.exceptions.CommandExecuteException;
import org.ucm.tp1.logic.Game;

public class UpdateCommand extends Command{
	public UpdateCommand() {
		super("none","n","","[n]one | []: update");
	}
	
	public boolean execute(Game g) throws CommandExecuteException {
		g.updateGame();
		return true;
	}
	
	public Command parse(String[] words) {
		if (matchCommandName(words[0]) || words[0].isEmpty()) {
			if (words.length != 1) {
				System.err.println(incorrectArgsMsg);
				return null;
			}
			return this;
		}
		
		return null;
	  }
}
