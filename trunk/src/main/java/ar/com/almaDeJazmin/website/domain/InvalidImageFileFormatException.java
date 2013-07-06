package ar.com.almaDeJazmin.website.domain;

public class InvalidImageFileFormatException extends ValidationException {

	private static final long serialVersionUID = 383655049418144399L;

	public InvalidImageFileFormatException() {
	}

	public InvalidImageFileFormatException(String message) {
		super(message);
	}

	public InvalidImageFileFormatException(Throwable cause) {
		super(cause);
	}

	public InvalidImageFileFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public String getMessage() {
		return "invalid.file.format";
	}
}
