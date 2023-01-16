package org.ucm.tp1.control.commands;

import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class SerializeCommand extends Command{
	
		public SerializeCommand() {
			super("serialize","z","","Seriali[z]e: Serializes the board.");
		}
		
		public boolean execute(Game game) {
			System.out.println(game.toSerialize());
			return false;
		}
		
		public Command parse (String[] words) throws CommandParseException {
			return this.parseNoParamsCommand(words);
		}
		
}
