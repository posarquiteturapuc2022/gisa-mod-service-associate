package br.com.posarquiteturapuc2022.exception;

public class PrestadorCadastradoException extends RuntimeException {
	
	private static final long serialVersionUID = -1607275301073224272L;

	public PrestadorCadastradoException(String message, Throwable cause) {
		super(message, cause);
	}

	public PrestadorCadastradoException(String message) {
		super(message);
	}

}
