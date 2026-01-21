package tp.tp1.controller;

import tp.tp1.game.Game;

public class ListCommand extends Command {
	public ListCommand(String name, String shortcut, String details, String help){
		super(name, shortcut, details, help);
	}
	
	public boolean execute(Game g, Controller control) {
		System.out.println(g.listGame());
		return true;
	}
	
	public Command parse(String[] s) {
		if(s[0].equalsIgnoreCase("list") || s[0].equalsIgnoreCase("l")) {
			return this;
		}
		else return null;
	}
}
