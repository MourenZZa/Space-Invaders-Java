package tp.tp1;
import tp.tp1.game.*;
import tp.tp1.Exceptions.InputError;
import tp.tp1.Exceptions.NoLevelException;
import tp.tp1.Exceptions.WrongInputException;
import tp.tp1.controller.*;
import java.util.*;

public class SpaceInvaders {

	
	public static void main(String[] args)  {
		Game g;
		Scanner scan = new Scanner( System.in);
		try {
		try {
			try {
				if(args.length == 1) {
					g = new Game(args[0],"",false); 	
				}else if(args.length == 2) {
					g = new Game(args[0],args[1],true); 
				} else {
					scan.close();
					throw new WrongInputException("Error: More than one Level and one seed inputs");
				}
			} catch (WrongInputException e) {
				System.out.format(e.getMessage() + "%n");
				throw new InputError();
			}


		Controller c = new Controller(g, scan);
		c.run();
		
		} catch( NoLevelException e) {
			System.out.println("No available level selected.");
			scan.close();
			throw new InputError();
		}
		}
		catch( InputError ie) {
			System.out.println("Select EASY/HARD/INSANE and a seed separated by space.");
		}

		
	}
	
}

