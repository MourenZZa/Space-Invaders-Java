package tp.tp1.game;
import tp.tp1.util.*;

public class BoardPrinter extends GamePrinter{
	private int numRows; 
	private int numCols;
	private String[][] board;
	private final String space = " ";
	
	public BoardPrinter () {
		super();
		this.numRows = Game.getMaxFil();
		this.numCols = Game.getMaxCol();
		}
	
	public void codifyGame() {
		board = new String[numRows][numCols];
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numCols; j++) {
				board[i][j] =  game.characterAtToString(i, j);
			}
		}
	}
	
	public String toString() {
		codifyGame();
		
		int cellSize = 7;
		int marginSize = 2;
		String vDelimiter = "|";
		String hDelimiter = "-";
		
		String rowDelimiter = MyStringUtils.repeat(hDelimiter, (numCols * (cellSize + 1)) - 1);
		String margin = MyStringUtils.repeat(space, marginSize);
		String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);
		
		StringBuilder str = new StringBuilder();
		str.append(game.infoToString());
		str.append(lineDelimiter);
		
		for(int i=0; i<numRows; i++) {
				str.append(margin).append(vDelimiter);
				for (int j=0; j<numCols; j++) {
					str.append( MyStringUtils.centre(board[i][j], cellSize)).append(vDelimiter);
				}
				str.append(lineDelimiter);
		}
		return str.toString();
	}

	@Override
	protected GamePrinter parse(String name) {
		if(name.equalsIgnoreCase("boardprinter")){
			return new BoardPrinter();
		}
		return null;
	}


}
