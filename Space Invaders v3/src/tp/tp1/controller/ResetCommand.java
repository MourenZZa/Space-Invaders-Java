package tp.tp1.controller;

import tp.tp1.game.Game;

public class ResetCommand extends Command {
	public ResetCommand(String name, String shortcut, String details, String help){
		super(name, shortcut, details, help);
	}
	
	public boolean execute(Game g, Controller control) {
		System.out.println("Restarting...");
		g.initGame();
		return true;
	}
	
	public Command parse(String[] s) {
		if(s[0].equalsIgnoreCase("reset") || s[0].equalsIgnoreCase("r")) {
			return this;
		}
		else return null;
	}
	
}
