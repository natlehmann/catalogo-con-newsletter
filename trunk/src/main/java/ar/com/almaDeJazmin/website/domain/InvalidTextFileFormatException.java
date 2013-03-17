package ar.com.almaDeJazmin.website.domain;

public class InvalidTextFileFormatException extends Exception {

	private static final long serialVersionUID = -4588571019093844172L;

	public InvalidTextFileFormatException() {
	}

	public InvalidTextFileFormatException(String message) {
		super(message);
	}

	public InvalidTextFileFormatException(Throwable cause) {
		super(cause);
	}

	public InvalidTextFileFormatException(String message, Throwable cause) {
		super(message, cause);
	}

}
