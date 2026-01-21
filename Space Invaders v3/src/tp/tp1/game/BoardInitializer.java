package tp.tp1.game;
import tp.tp1.*;

import tp.tp1.game.characters.*;

public class BoardInitializer {

	
	private Level l;
	private GameObjectBoard board;
	private Game g;
	
	
	public GameObjectBoard initialize(Game game, Level l) {
		this.l = l;
		this. g = game;
		board = new GameObjectBoard(Game.DIM_Y, Game.DIM_X);

		initializeOvni();
		initializeRegularAliens();
		initializeDestroyerAliens();
	return board;
	}
	private void initializeOvni() {
		Ovni ovni = new Ovni(g);
		board.add(ovni);
	}
	private void initializeRegularAliens() {
		
		int init_Row = 1;	  //Fila inicial de las naves regulares
		int init_Column = 3;  //Columna inicial de las naves regulares	
		
		int c = init_Column;
		int f = init_Row;
		
		for (int i = 0; i < l.getRegular(); i++, c++) {
			RegularShip regular = new RegularShip(g,f,c,2);
			board.add(regular);
			
			if((i+1)% 4 == 0) {
				f++;
				c = init_Column - 1;
			}
		}
	}
	private void initializeDestroyerAliens () {
		int init_Row = 3;	  //Fila inicial de las naves destructoras
		int init_Column = 4;  //Columna inicial de las naves destructoras	
		
		if(l.toString() == "EASY"){
			init_Row = 2;
		} else if(l.toString() == "INSANE") {
			init_Column = 3;
		}
		
		int c = init_Column;
		int f = init_Row;
		
		for (int i = 0; i < l.getDestroyer(); i++, c++) {
			DestroyerShip Destroyer = new DestroyerShip(g,f,c,1,i);
			board.add(Destroyer);
			
			if ((i + 1) % 4 == 0) {	//Si llenamos una fila, saltamos una
				f++;
				c = init_Column;
			}
		}
	}
}

