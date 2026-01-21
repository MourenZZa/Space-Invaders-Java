package tp.tp1.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import tp.tp1.Exceptions.CommandParseException;
import tp.tp1.game.*;

public class SaveCommand extends Command{

	public SaveCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);
	}

	public boolean execute(Game game,Controller control)  {
		
		System.out.println("Enter the name of the file to save ");
		String filename = Controller.in.nextLine().trim() + ".dat";
		
		String output = game.serialize();
		
	try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) 
	{	
		bw.write(output);
		System.out.println("The game has been saved successfully in " + filename);
	} 
	catch(IOException e) {
		System.out.println("There was an error saving the game");
	} 
		return false;
	}

	@Override
	public Command parse(String[] s) throws CommandParseException {
		if(s[0].equalsIgnoreCase("save") || s[0].equalsIgnoreCase("v")) {
			return this;
		}
		else return null;
	}
}

