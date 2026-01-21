package tp.tp1.game;

import tp.tp1.game.characters.Bomb;

public abstract class GameObject implements IAttack {
	protected int fil,col;
	protected int live;
	protected Game game;

	public GameObject( Game game, int fila, int columna, int live) {
		this.fil = fila;
		this.col = columna;
		this. game = game;
		this.live = live;
	}
	
	public GameObject() {}
	
	public int get_row() {
		return this.fil;
	}
	
	public int get_col() {
		return this.col;
	}
	
	public boolean isAlive() {
		return this.live > 0;
	}
	public int getLive() {
		return this.live;
	}
	
	public boolean isOnPosition(int fila, int columna) {
		return (fila == fil && columna == col);
	}
	
	public void getDamage (int damage) {
		this.live = damage >= this.live ? 0 : this.live - damage;
	}
	
	public boolean isOut() {
		return !game.isOnBoard(fil,col);
	}
	
	public abstract GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier, Game newgame);
	
	public void addBomb(Bomb bomb) {
		System.out.println("Bomba en saco roto");
	}	
	public abstract void computerAction();
	public abstract void onDelete();
	public abstract void move(int numCells);
	public abstract String toString();
	public boolean isOnBorder() {return false;}
	
	public int get_n_bomb() {return -1;};
	public boolean isLaser() {return false;};
	public boolean BombOwner(GameObject bomb) {return false;};
	protected abstract String serialize();
}
