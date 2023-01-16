package org.ucm.tp1.exceptions;

@SuppressWarnings("serial")
public class CommandParseException extends GameException{
	public CommandParseException(String message) {
		super(message);
	}
}