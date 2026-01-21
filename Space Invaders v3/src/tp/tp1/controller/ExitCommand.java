package tp.tp1.controller;

import tp.tp1.game.Game;

public class ExitCommand extends Command {
	public ExitCommand(String name, String shortcut, String details, String help){
		super(name, shortcut, details, help);
	}
	
	public boolean execute(Game g, Controller control) {
		g.exit();
		return false;
	}
	
	public Command parse(String[] s) {
		if(s[0].equalsIgnoreCase("exit") || s[0].equalsIgnoreCase("e")) {
			return this;
		}
		else return null;
	}
}

