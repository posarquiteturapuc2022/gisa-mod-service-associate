package br.com.posarquiteturapuc2022.exception;

public class PrestadorFeignNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 3230839809815501948L;

	public PrestadorFeignNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PrestadorFeignNotFoundException(String message) {
		super(message);
	}

}
