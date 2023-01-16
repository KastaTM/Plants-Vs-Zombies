package org.ucm.tp1.exceptions;

@SuppressWarnings("serial")
public class NoMoreVampiresException extends CommandExecuteException {
	public NoMoreVampiresException(String message) {
		super(message);
	}
}