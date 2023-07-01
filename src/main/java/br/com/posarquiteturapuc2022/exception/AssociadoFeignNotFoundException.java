package br.com.posarquiteturapuc2022.exception;

public class AssociadoFeignNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 3230839809815501948L;

	public AssociadoFeignNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public AssociadoFeignNotFoundException(String message) {
		super(message);
	}

}
