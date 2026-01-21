package tp.tp1.controller;
import tp.tp1.Exceptions.CommandExecuteException;
import tp.tp1.Exceptions.CommandParseException;
import tp.tp1.game.*;

import java.util.Scanner;
 
public class Controller {

	private Game game;
	public static Scanner in;
	private String unknownCommandMsg = "Wrong command.Type HELP for commands options \n";
	public PrinterTypes printertype;
	
	public Controller(Game g, Scanner scan){
		game = g;
		in = scan;
	}
	
	public void run(){
		
		printertype = PrinterTypes.BOARDPRINTER;
		GamePrinter printer = printertype.getObject(game);
		
		System.out.println(printer);

		while (!game.isFinished()){
			
			System.out.println("Command >");
			String[] words = in.nextLine().toLowerCase().trim().split ("\\s+");
	
			try {
				Command command = CommandGenerator.parseCommand(words);
				if (command != null) {
					if (command.execute(game, this))
						System.out.println(printer);
					}
				else {
					System.out.format(unknownCommandMsg);
				}
			} catch( CommandParseException | CommandExecuteException e) {
				System.out.format(e.getMessage() + " %n %n");
			}	
		}
		System.out.println(game.getWinnerMessage());		
	}
	
	public void setGame(Game game) {
		this.game = game;
	}

}












