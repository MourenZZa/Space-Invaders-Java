package tp.tp1.game;

	
public abstract class GamePrinter {

	Game game;
	
	public GamePrinter() {}
	
	protected abstract void codifyGame();
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public abstract String toString();

	protected abstract GamePrinter parse(String name);

}