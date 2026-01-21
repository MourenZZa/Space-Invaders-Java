package tp.tp1.controller;

import tp.tp1.game.Game;

public class HelpCommand extends Command{
	public HelpCommand(String name, String shortcut, String details, String help){
		super(name, shortcut, details, help);
	}
	
	public boolean execute(Game g, Controller control) {
		System.out.print(CommandGenerator.commandHelp());
		return false;
	}
	
	public Command parse(String[] s) {
		if(s[0].equalsIgnoreCase("help") || s[0].equalsIgnoreCase("h")) {
			return this;
		}
		else return null;
	}
}

