package org.ucm.tp1.control.commands;

import org.ucm.tp1.exceptions.CommandParseException;

public class CommandGenerator {

	
	private static Command[] availableCommands = {
			new AddCommand(),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new UpdateCommand(),
			new GarlicPushCommand(),
			new LightFlashCommand(),
			new AddBloodBankCommand(),
			new SuperCoinsCommand(),
			new AddVampireCommand(),
			new SaveCommand(),
			new SerializeCommand()
			};
	
	public static Command parseCommand(String[] words) throws CommandParseException {
		boolean find = false;
		Command c = null;
		for(int i = 0; !find && i < availableCommands.length; i++) {
			c = availableCommands[i].parse(words);
			if(c != null) {
				find = true;
			}
		}
		return c;
	}
	
	public static String commandHelp() {
		String s = "";
		s+= "Available commands:\n";
		for(int i = 0; i < availableCommands.length; i++) {
			s += availableCommands[i].helpText();
		}
		return s;
	}
	
}
