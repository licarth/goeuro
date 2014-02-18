package goeuro;

public class InvalidArgumentsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidArgumentsException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public InvalidArgumentsException(String message) {
		super(message);
	}
}
