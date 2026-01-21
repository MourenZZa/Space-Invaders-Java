package tp.tp1.Exceptions;

public class CommandExecuteException extends Exception{

	private static final long serialVersionUID = 1L;

	public CommandExecuteException() {
		super();
	}
	
	public CommandExecuteException(String message) {
		super(message);
	}
}

