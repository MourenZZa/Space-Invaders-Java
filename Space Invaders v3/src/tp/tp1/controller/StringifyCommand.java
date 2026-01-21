package tp.tp1.controller;

import tp.tp1.game.Game;
import tp.tp1.game.GamePrinter;
import tp.tp1.game.PrinterTypes;

public class StringifyCommand extends Command {

	public StringifyCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);
	}

	public boolean execute(Game g, Controller control) {
		PrinterTypes printertype = PrinterTypes.STRINGIFIER;
		GamePrinter printer = printertype.getObject(g);
		
		System.out.println(printer);
		return false;
	}

	public Command parse(String[] s) {
		if(s[0].equalsIgnoreCase("stringify") || s[0].equalsIgnoreCase("f")) {
			return this;
		}
		else return null;
		}
}
	
