package tp.tp1.controller;

import tp.tp1.Exceptions.CommandExecuteException;
import tp.tp1.Exceptions.NoShockWaveException;
import tp.tp1.game.Game;

public class ShockCommand extends Command {
	
	public ShockCommand(String name, String shortcut, String details, String help){
		super(name, shortcut, details, help);
	}
	
	public boolean execute(Game g, Controller control) throws CommandExecuteException {
		try {
			if(g.shockWave()) {
				System.out.println("S U P E R - E X T R E M E  S H O C K W A V E!!");
				g.update();
				return true;
			}
			else 
				g.update();
		} catch (NoShockWaveException e) {
			throw new CommandExecuteException (e.getMessage());
		}
		return false;
		}
	
	
	public Command parse(String[] s) {
		if(s.length == 1 && (s[0].equalsIgnoreCase("shockwave") || s[0].equalsIgnoreCase("w"))) {
			return this;
		}
		else return null;
	}
}
