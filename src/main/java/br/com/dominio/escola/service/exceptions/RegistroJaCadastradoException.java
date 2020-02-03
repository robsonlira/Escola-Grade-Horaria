package br.com.dominio.escola.service.exceptions;

public class RegistroJaCadastradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public RegistroJaCadastradoException(String msg) {
		super(msg);
	}
	
	public RegistroJaCadastradoException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
