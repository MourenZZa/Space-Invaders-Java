package tp.tp1.controller;

import tp.tp1.game.Game;
import tp.tp1.game.PrinterTypes;

public class ListPrintersCommand extends Command {

	public ListPrintersCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);
	}

	@Override
	public boolean execute(Game g, Controller control) { 
		System.out.println(PrinterTypes.printerHelp(g));
		return false;

	}

	@Override
	public Command parse(String[] s) {
		if(s[0].equalsIgnoreCase("ListPrinters") || s[0].equalsIgnoreCase("p")) {
			return this;
		}
		else return null;
		}
}


