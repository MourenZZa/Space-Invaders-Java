package tp.tp1.controller;

import tp.tp1.game.Game;

public class UpdateCommand extends Command {
	public UpdateCommand(String name, String shortcut, String details, String help){
		super(name, shortcut, details, help);
	}
	
	public boolean execute(Game g, Controller control) {
		g.update();
		return true;
	}
	
	public Command parse(String[] s) {
		if(s[0].equalsIgnoreCase("") || s[0].equalsIgnoreCase("n")) {
			return this;
		}
		else return null;
	}
}
