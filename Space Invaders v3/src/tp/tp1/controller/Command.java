package tp.tp1.controller;
import tp.tp1.Exceptions.CommandExecuteException;
import tp.tp1.Exceptions.CommandParseException;
import tp.tp1.game.*;

public abstract class Command {
		
	protected final String name;
	protected final String shortcut;
	protected final String details;		
	protected final String help;
	
	protected static final String incorrectNumArgsMsg = "Incorrect number of arguments";
	protected static final String incorrectArgsMsg = "Incorrect argument format";
	
	public Command(String name, String shortcut, String details, String help){
		this.name = name;
		this. shortcut = shortcut;
		this.details = details;
		this.help = help;
	}
	
	protected boolean matchCommandName(String name) {
		return (this.shortcut.equalsIgnoreCase(name) || this.name.equalsIgnoreCase(name));
		}

	public abstract boolean execute(Game g, Controller control) throws CommandExecuteException;
	
	public abstract Command parse(String[] s) throws CommandParseException;
	
	public String helptext() {
		return details + " : " + help + "\n";
	}
}
