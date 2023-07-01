package br.com.posarquiteturapuc2022.exception;

public class AssociadoCadastradoException extends RuntimeException {
	
	private static final long serialVersionUID = 3230839809815501948L;

	public AssociadoCadastradoException(String message, Throwable cause) {
		super(message, cause);
	}

	public AssociadoCadastradoException(String message) {
		super(message);
	}

}
