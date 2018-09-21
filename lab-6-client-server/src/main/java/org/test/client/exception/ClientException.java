package org.test.client.exception;

public class ClientException extends Exception {

	private static final long serialVersionUID = 1L;

	public ClientException(String msg) {
		super(msg);
	}

	public ClientException(String msg, Throwable t) {
		super(msg, t);
	}

	public ClientException(Throwable t) {
		super(t);
	}

}
