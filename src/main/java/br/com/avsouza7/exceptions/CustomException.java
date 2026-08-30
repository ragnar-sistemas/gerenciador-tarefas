package br.com.avsouza7.exceptions;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String debugMessage;

	public CustomException() {
	}

	public CustomException(String message) {
		super(message);
	}

	public CustomException(String message, String debugMessage) {
		this(message);
		this.debugMessage = debugMessage;
	}

	public CustomException debugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
		return this;
	}

	public String debugMessage() {
		return this.debugMessage;
	}

}
