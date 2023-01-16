package org.ucm.tp1.control.commands;

import org.ucm.tp1.exceptions.CommandExecuteException;
import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.exceptions.NotEnoughCoinsException;
import org.ucm.tp1.logic.Game;

public class GarlicPushCommand extends Command{
	
	public GarlicPushCommand() {
		super("garlic","g","","[g]arlic : pushes back vampires");
	}
	
	public boolean execute(Game g) throws CommandExecuteException {
		boolean ret = false;
		try {
			if(g.garlicPush()){
				g.updateGame();
				return true;
			}
		}
		catch(NotEnoughCoinsException nec) {
			System.out.println(nec.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: Fail to garlic push"));
		}
		return ret;
	}
	
	public Command parse(String[] words) throws CommandParseException {
		  return this.parseNoParamsCommand(words);
	  }
}
