package ar.com.almaDeJazmin.website.domain;

public class EmailException extends Exception {

	private static final long serialVersionUID = -5533666616818198255L;

	public EmailException() {
	}

	public EmailException(String message) {
		super(message);
	}

	public EmailException(Throwable cause) {
		super(cause);
	}

	public EmailException(String message, Throwable cause) {
		super(message, cause);
	}

}
