package org.ucm.tp1.control.commands;

import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class HelpCommand extends Command{
	public HelpCommand() {
		super("help","h","","[h]elp: show this help");
	}
	
	public boolean execute(Game g) {
		System.out.println(CommandGenerator.commandHelp());
		return false;
	}
	
	public Command parse(String[] words) throws CommandParseException {
		  return this.parseNoParamsCommand(words);
	  }
}
