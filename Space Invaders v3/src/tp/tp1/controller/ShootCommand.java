package tp.tp1.controller;
import tp.tp1.Exceptions.AlreadyShootException;
import tp.tp1.Exceptions.CommandExecuteException;
import tp.tp1.game.*;

public class ShootCommand extends Command {
	
	public ShootCommand(String name, String shortcut, String details, String help){
		super(name, shortcut, details, help);
	}
	public boolean execute(Game g, Controller control) throws CommandExecuteException {
		g.enableMissile();
		try {
			if(!g.shootMissile()) {
				
			}
			else 
				g.enableMissile();
		} catch (AlreadyShootException e) {
			throw new CommandExecuteException(e.getMessage());
		}
			g.update();
			return true;
		}
	
	public Command parse(String[] s) {
		if(s[0].equalsIgnoreCase("shoot") || s[0].equalsIgnoreCase("s")) {
			return this;
		}
		else return null;
		}
}


	
	


