package org.ucm.tp1.control.commands;

import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class SuperCoinsCommand extends Command{
	
	public SuperCoinsCommand() {
		super("coins","c","","[c]oins: add 1000 coins");
	}
	
	public boolean execute(Game g) {
		g.addCoins(1000);
		return true;
	}
	
	public Command parse(String[] words) throws CommandParseException {
		  return this.parseNoParamsCommand(words);
	  }
}
