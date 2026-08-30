package br.com.avsouza7.exceptions;

public class LeituraException extends RuntimeException {

	private static final long serialVersionUID = -1623884951568218350L;

	public LeituraException(String mensagem, Throwable throwable) {
		super(mensagem, throwable);
	}

}
