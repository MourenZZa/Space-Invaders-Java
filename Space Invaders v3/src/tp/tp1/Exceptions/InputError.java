package tp.tp1.Exceptions;

public class InputError extends Exception {
	
	private static final long serialVersionUID = 1L;

	public InputError() {
		super();
	}
	
	public InputError(String message) {
		super(message);
	}
	
}
