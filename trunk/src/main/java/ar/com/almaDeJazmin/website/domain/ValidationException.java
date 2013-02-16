package ar.com.almaDeJazmin.website.domain;

public class ValidationException extends Exception {

	private static final long serialVersionUID = 7190982891152580000L;

	public ValidationException() {
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(Throwable cause) {
		super(cause);
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

}
