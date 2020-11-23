package br.com.cardif.exception;

public class ValidacaoNotFoundException extends RuntimeException {

	private String campo;

	private static final long serialVersionUID = -4995523139534712450L;

	public ValidacaoNotFoundException(String errorMessage) {
		super(errorMessage);
	}

	public ValidacaoNotFoundException(String campo, String errorMessage) {
		super(errorMessage);
		this.campo = campo;
	}

	public String getCampo() {
		return campo;
	}
}
