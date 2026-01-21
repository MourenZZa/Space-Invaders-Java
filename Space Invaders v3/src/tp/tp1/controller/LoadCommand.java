package tp.tp1.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import tp.tp1.game.*;
import tp.tp1.Exceptions.*;

public class LoadCommand extends Command{

	public LoadCommand(String name, String shortcut, String details, String help) {
		super(name,shortcut, details, help);
	}

	public boolean execute(Game game,Controller control)  {
		
		System.out.println("Enter the name of the file to load ");
		String filename = Controller.in.nextLine().trim() + ".dat";
		
	try (BufferedReader br = new BufferedReader(new FileReader(filename))) 
	{	
		String str = br.readLine().trim();
		
		if(str.equals("-- Space Invaders v2.0 --")) {
			str = br.readLine().trim();
			if (str.equals("")) {
				
				Game g = game.load(br);
				control.setGame(g);
				System.out.println("Game successfuly loaded from file " + filename);
				
				PrinterTypes printertype = PrinterTypes.BOARDPRINTER;
				GamePrinter printer = printertype.getObject(g);
				
				System.out.println(printer);
				return false;
			}else {throw new FileContentsException("Invalid file. Not line space after Headboard");}
		} else {throw new FileContentsException("Invalid file. Wrong Headboard");} 
	} 
	catch(FileNotFoundException e) {
		System.out.format(e.getMessage() + " %n %n");
	} 
	catch(FileContentsException e) {
		System.out.format(e.getMessage() + " %n %n");
	}
	catch(NullPointerException e) {
		System.out.println("Invalid file. There are missing lines in the file");
	}
	catch (IOException e) {
		System.out.format(e.getMessage() + " %n %n");
	} 
		return false;
}



	@Override
	public Command parse(String[] s) throws CommandParseException {
		if(s[0].equalsIgnoreCase("load") || s[0].equalsIgnoreCase("d")) {
			return this;
		}
		else return null;
	}
	
}

