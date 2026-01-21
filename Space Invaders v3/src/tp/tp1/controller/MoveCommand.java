package tp.tp1.controller;
import tp.tp1.Exceptions.CommandExecuteException;
import tp.tp1.Exceptions.CommandParseException;
import tp.tp1.Exceptions.OutOfWorldEception;
import tp.tp1.game.Game;


public class MoveCommand extends Command {
	private String box;
	private String dir;
	
	public MoveCommand(String name, String shortcut, String details, String help){
		super(name, shortcut, details, help);    
	}
	
	public MoveCommand(MoveCommand m, String d, String b){
		super(m.name, m.shortcut, m.details, m.help);
		box  = b;
		dir = d;
	}
	
	public boolean execute(Game g, Controller control) throws CommandExecuteException {
		if(dir.equalsIgnoreCase("l") || dir.equalsIgnoreCase("left")) {
			if(box.equalsIgnoreCase("1")) {
				return UCM_Movement(g,1,0);
			} else return UCM_Movement(g,2,0);		
		}
		else {
			if(box.equalsIgnoreCase("1")) {
				return UCM_Movement(g,1,1);
			} else return UCM_Movement(g,2,1);
		}
	}
	
	
	public Command parse(String[] s) throws CommandParseException {
		
		if(s.length == 3) {
			
			Command m = new MoveCommand(this ,s[1], s[2]);
			
			if(s[0].equalsIgnoreCase("m") || s[0].equalsIgnoreCase("move")){
				if(s[1].equalsIgnoreCase("l") || s[1].equalsIgnoreCase("left")) {
					if(s[2].equalsIgnoreCase("1")) {
						return m;
					} else if (s[2].equalsIgnoreCase("2")) {
						return m;
					} throw new CommandParseException("You can only move 1 or 2 squares per turn");
				}
				else if(s[1].equalsIgnoreCase("r") || s[1].equalsIgnoreCase("right")) {				
					if(s[2].equalsIgnoreCase("1")) {
						return m;
					} else if (s[2].equalsIgnoreCase("2")) {
						return m;
					} throw new CommandParseException("You can only move 1 or 2 squares per turn");
				}
				throw new CommandParseException("You can only move left (l) or right (r)"); 
			}
			throw new CommandParseException("Error : Wrong command move input");
		}
		else return null;
	}
	
	private boolean UCM_Movement(Game g, int movimiento, int direccion) throws CommandExecuteException {
			if (direccion != 1 && direccion != 2) movimiento *= -1;
			try {
				if(g.move(movimiento)) {
					return true;
				}
				else {
					return false;
				}
			} catch (OutOfWorldEception e) {
				throw new CommandExecuteException(e.getMessage());
			}

	}
}

