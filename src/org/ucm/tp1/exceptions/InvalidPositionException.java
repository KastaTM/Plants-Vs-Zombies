package org.ucm.tp1.exceptions;

@SuppressWarnings("serial")
public class InvalidPositionException extends CommandExecuteException{
	public InvalidPositionException(String message) {
		super(message);
	}
}
