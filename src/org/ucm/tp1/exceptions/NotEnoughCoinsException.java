package org.ucm.tp1.exceptions;

@SuppressWarnings("serial")
public class NotEnoughCoinsException extends CommandExecuteException {
	public NotEnoughCoinsException(String message) {
		super(message);
	}
}
