package org.ucm.tp1.control.commands;

import org.ucm.tp1.exceptions.CommandExecuteException;
import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class AddVampireCommand extends Command{
	private int x;
	private int y;
	private String type="";
	public AddVampireCommand() {
		super("vampire","v","","[v]ampire [<type>] <x> <y>. Type = {\"\"|\"D\"|\"E\"}: add a vampire in position x, y");
		this.x = -1;
		this.y = -1;
	}
	
	public boolean execute(Game g) throws CommandExecuteException {
		boolean ret = false;
			try {
				if(g.addVampire(type, y, x)){
					ret = true;
				}
			}
			catch(CommandExecuteException ex) {
				System.out.println(ex.getMessage());
				throw new CommandExecuteException(String.format("[ERROR]: Failed to add this vampire"));
			}
		return ret;
	}
	
	public Command parse(String[] words) throws CommandParseException {
		if (matchCommandName(words[0])) {
			try {
			if (words.length == 3) {
				type="";
				x = Integer.parseInt(words[1]);
				y = Integer.parseInt(words[2]);
			}
			else if(words.length == 4) {
				type = words[1];
				x = Integer.parseInt(words[2]);
				y = Integer.parseInt(words[3]);
			}
			}
			catch(NumberFormatException ex) {
				//System.out.println(ex.getMessage());
				throw new CommandParseException ("[ERROR]: Unvalid argument for add vampire command, number expected: [v]ampire [<type>] <x> <y>. Type = {\"\"|\"D\"|\"E\"}");
			}
			return this;
		}
		
		return null;
	  }
	
	
}
