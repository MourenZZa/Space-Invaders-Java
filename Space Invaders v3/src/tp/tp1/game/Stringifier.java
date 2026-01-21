package tp.tp1.game;

public class Stringifier extends GamePrinter {

	String boardString;
	
	public Stringifier () {
		super();
			}
	
	
	protected void codifyGame() {
		boardString = game.serialize();
	}
	
	public String toString() {
		codifyGame();
		return boardString;
	}


	@Override
	protected GamePrinter parse(String name) {
		if(name.equalsIgnoreCase("Stringifier")){
			return new BoardPrinter();
		}
		return null;
	}

}

