package org.ucm.tp1.control.commands;

import org.ucm.tp1.exceptions.CommandExecuteException;
import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.exceptions.NotEnoughCoinsException;
import org.ucm.tp1.logic.Game;

public class LightFlashCommand extends Command{
	public LightFlashCommand() {
		super("light","l","","[l]ight: kills all the vampires");
	}
	
	public boolean execute(Game g) throws CommandExecuteException {
		boolean ret=false;
		try{
			if(g.lightFlash()) {
			g.updateGame();
			ret= true;
			}
		}
		catch(NotEnoughCoinsException nec) {
			System.out.println(nec.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: Failed to light flash"));
		}
		return ret;
	}
	
	public Command parse(String[] words) throws CommandParseException {
		  return this.parseNoParamsCommand(words);
	}
}
