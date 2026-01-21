package tp.tp1.Exceptions;

public class CommandParseException extends Exception {

	private static final long serialVersionUID = 1L;

	public CommandParseException() {
		super();
	}
	
	public CommandParseException(String message) {
		super(message);
	}
}

