package tp.tp1.controller;
import tp.tp1.Exceptions.CommandParseException;

public class CommandGenerator {
	
	private static Command[] availableCommands = {
			new ListCommand("list", "l", "[L]ist", "Prints the list of availabe ships"),
			new HelpCommand("help", "h", "[H]elp", "Prints this help message"),
			new ResetCommand("reset", "r", "[R]eset", "Starts a new game"),
			new ExitCommand("exit", "e", "[E]xit", "Terminates the program"),
			new UpdateCommand("none", "n", "[none]", "Skips one cycle"),
			new MoveCommand("move", "m","[M]ove <left|right><1|2>","Moves UCM-Ship to the indicated direction" ),
			new ShockCommand("shockwave", "w", "shock[W]ave", "UCM-Ship release a shock wave"),
			new ShootCommand("shoot", "s", "[S]hoot", "UCM-Ship launches a missile"),
			new BuySuperMissileCommand("buy", "b", "[B]uy", "Buy a super missile for 20 points"),
			new StringifyCommand("Stringify", "f", "Stringi[F]y", "Show stringified game"),
			new ListPrintersCommand("ListPrinters", "p", "list[P]rinters", "Display the diferent types of printers"),
			new SaveCommand("Save", "v", "Sa[V]e", "Save the game in a file"), 
			new LoadCommand("Load", "d", "loa[D]", "Load from a file the game")};
			

	public static Command parseCommand(String[] commandWords) throws CommandParseException {
		for(int i = 0; i < availableCommands.length; i++) {
			if(availableCommands[i].parse(commandWords) != null) {
				return availableCommands[i].parse(commandWords);
			}
		}
		throw new CommandParseException("Error: Wrong command input. Write help for more help");
	}
	
	public static String commandHelp() {
		String s = "";
		for(int i = 0; i < availableCommands.length; i++) {
			s += availableCommands[i].helptext() + "\n";
		}
		return s;
	}
}

