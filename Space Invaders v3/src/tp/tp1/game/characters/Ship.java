package tp.tp1.game.characters;

import tp.tp1.game.Game;
import tp.tp1.game.GameObject;

public abstract class Ship extends GameObject {
	
	//Constructor
	public Ship(Game g, int f, int c, int live) {
		super(g, f, c, live);
	}
	
	//Constructor del GameObjectGenerator
	public Ship() {}
	
	//Muestra las estadisticas de las naves
	public static String showStats() {
		return UCMShip.showStats() + RegularShip.showStats() + DestroyerShip.showStats() + Ovni.showStats();
	}
	
	//GameObject methods--------------------------------------------------
	
		@Override
		public abstract boolean isOnBorder();
	
		@Override
		public boolean isLaser() { return false;}
}
