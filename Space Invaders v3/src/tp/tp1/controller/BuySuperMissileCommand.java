package tp.tp1.controller;

import tp.tp1.Exceptions.CommandExecuteException;
import tp.tp1.Exceptions.NotPoints;
import tp.tp1.game.Game;

public class BuySuperMissileCommand extends Command {

	public BuySuperMissileCommand(String name, String shortcut, String details, String help){
		super(name, shortcut, details, help);    
	}
	
	@Override
	public boolean execute(Game g, Controller control) throws CommandExecuteException {
		try {
			if(g.buySuperMissile())
			return true;
			else {
			}
		} catch (NotPoints e) {
			System.out.format(e.getMessage());
			throw new CommandExecuteException("");
		}
		return false;
	}

	@Override
	public Command parse(String[] s) {

		if(s[0].equalsIgnoreCase("buy") || s[0].equalsIgnoreCase("b")) {
			return this;
		}
		else return null;
		
	}

}

